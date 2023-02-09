package com.edival.recioblog.data.repository

import android.net.Uri
import com.edival.recioblog.core.Constants
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class UsersRepositoryImpl
@Inject constructor(
    private val usersRef: CollectionReference, private val storageUsersRef: StorageReference
) : UsersRepository {
    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.id?.let { id -> usersRef.document(id).set(user).await() }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val updates = hashMapOf<String, Any?>(
                Constants.F_USERNAME to user.username, Constants.F_IMG_URL to user.imgUrl
            )
            user.id?.let { id -> usersRef.document(id).update(updates).await() }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun uploadImage(file: File): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val usersRef = storageUsersRef.child(file.name).also { reference ->
                reference.putFile(fromFile).await()
            }
            val uri = usersRef.downloadUrl.await()
            return Response.Success(uri.toString())
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val listener = usersRef.document(id).addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }
        awaitClose { listener.remove() }
    }
}