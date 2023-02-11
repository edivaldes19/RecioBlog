package com.edival.recioblog.di

import com.edival.recioblog.core.Constants
import com.edival.recioblog.data.repository.AuthRepositoryImpl
import com.edival.recioblog.data.repository.PostsRepositoryImpl
import com.edival.recioblog.data.repository.UsersRepositoryImpl
import com.edival.recioblog.domain.repository.AuthRepository
import com.edival.recioblog.domain.repository.PostsRepository
import com.edival.recioblog.domain.repository.UsersRepository
import com.edival.recioblog.domain.use_cases.auth.*
import com.edival.recioblog.domain.use_cases.posts.*
import com.edival.recioblog.domain.use_cases.users.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

    @Provides
    @Named(Constants.COLL_USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference {
        return db.collection(Constants.COLL_USERS)
    }

    @Provides
    @Named(Constants.COLL_USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference {
        return storage.reference.child(Constants.COLL_USERS)
    }

    @Provides
    @Named(Constants.COLL_POSTS)
    fun providePostsRef(db: FirebaseFirestore): CollectionReference {
        return db.collection(Constants.COLL_POSTS)
    }

    @Provides
    @Named(Constants.COLL_POSTS)
    fun provideStoragePostsRef(storage: FirebaseStorage): StorageReference {
        return storage.reference.child(Constants.COLL_POSTS)
    }

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providePostsRepository(impl: PostsRepositoryImpl): PostsRepository = impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository): AuthUseCases = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        signUp = SignUp(repository),
        resetPassword = ResetPassword(repository),
        signOff = SignOff(repository)
    )

    @Provides
    fun provideUsersUseCase(repository: UsersRepository): UsersUseCases = UsersUseCases(
        create = Create(repository),
        update = Update(repository),
        getUserById = GetUserById(repository)
    )

    @Provides
    fun providePostsUseCases(repository: PostsRepository): PostsUseCases = PostsUseCases(
        create = CreatePost(repository),
        getPosts = GetPosts(repository),
        getPostsByUserId = GetPostsByUserId(repository),
        deletePost = DeletePost(repository),
        updatePost = UpdatePost(repository),
        likePost = LikePost(repository),
        unlikePost = UnlikePost(repository)
    )
}