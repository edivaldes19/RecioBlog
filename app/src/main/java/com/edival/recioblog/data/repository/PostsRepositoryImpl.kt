package com.edival.recioblog.data.repository

import android.net.Uri
import com.edival.recioblog.core.Constants
import com.edival.recioblog.domain.model.Post
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.repository.PostsRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostsRepositoryImpl @Inject constructor(
    @Named(Constants.COLL_USERS) private val usersRef: CollectionReference,
    @Named(Constants.COLL_POSTS) private val postsRef: CollectionReference,
    @Named(Constants.COLL_POSTS) private val storagePostRef: StorageReference,
    private val storage: FirebaseStorage
) : PostsRepository {
    override suspend fun create(post: Post, file: File?): Response<Boolean> {
        return try {
            file?.let { photo ->
                val fromFile = Uri.fromFile(photo)
                val ref = storagePostRef.child(photo.name)
                ref.apply {
                    putFile(fromFile).await()
                    downloadUrl.await().also { uri -> post.imgUrl = uri.toString() }
                }
            }
            postsRef.document().id.also { id ->
                post.id = id
                postsRef.document(id).set(post).await()
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun update(post: Post, file: File?): Response<Boolean> {
        return try {
            file?.let { photo ->
                post.imgUrl?.let { url ->
                    val httpsReference = storage.getReferenceFromUrl(url)
                    httpsReference.delete().await()
                }
                val fromFile = Uri.fromFile(photo)
                val ref = storagePostRef.child(photo.name)
                ref.apply {
                    putFile(fromFile).await()
                    downloadUrl.await().also { uri -> post.imgUrl = uri.toString() }
                }
            }
            val updates = hashMapOf<String, Any?>(
                Constants.F_NAME to post.name,
                Constants.F_DESC to post.description,
                Constants.F_IMG_URL to post.imgUrl,
                Constants.F_CTG to post.category
            )
            post.id?.let { id -> postsRef.document(id).update(updates).await() }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun delete(idPost: String, imgUrl: String?): Response<Boolean> {
        return try {
            imgUrl?.let { url ->
                val httpsReference = storage.getReferenceFromUrl(url)
                httpsReference.delete().await()
            }
            postsRef.document(idPost).delete().await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun like(idPost: String, idUser: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).update(Constants.F_LIKES, FieldValue.arrayUnion(idUser))
                .await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun unlike(idPost: String, idUser: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).update(Constants.F_LIKES, FieldValue.arrayRemove(idUser))
                .await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
        val listener = postsRef.addSnapshotListener { snapshot, error ->
            CoroutineScope(Dispatchers.IO).launch {
                val postRes = if (snapshot != null) {
                    val posts = snapshot.toObjects<Post>()
                    val idUserList = mutableListOf<String?>()
                    posts.forEach { post -> idUserList.add(post.idUser) }
                    val idUserSet = idUserList.toSet().toList()
                    idUserSet.map { idUser ->
                        async {
                            idUser?.let { id ->
                                val user = usersRef.document(id).get().await().toObject<User>()
                                posts.forEach { post -> if (post.idUser == id) post.user = user }
                            }
                        }
                    }.forEach { deferred -> deferred.await() }
                    Response.Success(posts)
                } else Response.Failure(error)
                trySend(postRes)
            }
        }
        awaitClose { listener.remove() }
    }

    override fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow {
        val listener = postsRef.whereEqualTo(Constants.F_ID_USER, idUser)
            .addSnapshotListener { snapshot, error ->
                val postRes = if (snapshot != null) {
                    val posts = snapshot.toObjects<Post>()
                    Response.Success(posts)
                } else Response.Failure(error)
                trySend(postRes)
            }
        awaitClose { listener.remove() }
    }
}