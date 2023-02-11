package com.edival.recioblog.data.repository

import android.net.Uri
import com.edival.recioblog.core.Constants
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl
@Inject constructor(
    @Named(Constants.COLL_USERS) private val usersRef: CollectionReference,
    @Named(Constants.COLL_USERS) private val storageUsersRef: StorageReference,
    private val storage: FirebaseStorage
) : UsersRepository {
    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.id?.let { id -> usersRef.document(id).set(user).await() }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun update(user: User, file: File?): Response<Boolean> {
        return try {
            file?.let { photo ->
                user.imgUrl?.let { url ->
                    val httpsReference = storage.getReferenceFromUrl(url)
                    httpsReference.delete().await()
                }
                val fromFile = Uri.fromFile(photo)
                val ref = storageUsersRef.child(photo.name)
                ref.apply {
                    putFile(fromFile).await()
                    downloadUrl.await().also { uri -> user.imgUrl = uri.toString() }
                }
            }
            val updates = hashMapOf<String, Any?>(
                Constants.F_USERNAME to user.username, Constants.F_IMG_URL to user.imgUrl
            )
            user.id?.let { id -> usersRef.document(id).update(updates).await() }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val listener = usersRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject<User>() ?: User()
            trySend(user)
        }
        awaitClose { listener.remove() }
    }
}