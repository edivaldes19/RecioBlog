package com.edival.recioblog.di

import com.edival.recioblog.core.Constants
import com.edival.recioblog.data.repository.AuthRepositoryImpl
import com.edival.recioblog.data.repository.UsersRepositoryImpl
import com.edival.recioblog.domain.repository.AuthRepository
import com.edival.recioblog.domain.repository.UsersRepository
import com.edival.recioblog.domain.use_cases.auth.*
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

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(Constants.USERS)

    @Provides
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(Constants.USERS)

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository): AuthUseCases = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logOut = LogOut(repository),
        signUp = SignUp(repository)
    )

    @Provides
    fun provideUsersUseCase(repository: UsersRepository): UsersUseCases = UsersUseCases(
        create = Create(repository),
        update = Update(repository),
        uploadImage = UploadImage(repository),
        getUserById = GetUserById(repository)
    )
}