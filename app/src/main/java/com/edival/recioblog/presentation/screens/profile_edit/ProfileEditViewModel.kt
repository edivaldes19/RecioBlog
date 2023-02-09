package com.edival.recioblog.presentation.screens.profile_edit

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.recioblog.core.Constants
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.use_cases.users.UsersUseCases
import com.edival.recioblog.presentation.utils.ComposeFileProvider
import com.edival.recioblog.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val usersUseCases: UsersUseCases
) : ViewModel() {
    var state: ProfileEditState by mutableStateOf(ProfileEditState())
        private set
    var isUsernameValid: Boolean by mutableStateOf(false)
        private set
    var usernameErrMsg: String? by mutableStateOf(null)
        private set
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    var uploadImageResponse by mutableStateOf<Response<String>?>(null)
        private set
    var stateDialog: MutableState<Boolean> = mutableStateOf(false)
    val resultingActivityHandler = ResultingActivityHandler()
    private var file: File? = null
    private var user = User()

    init {
        savedStateHandle.get<String>(Constants.USER_ARG)?.let { userStr ->
            user = User.fromJson(userStr)
            state = state.copy(username = user.username ?: "", imgUrl = user.imgUrl)
        }
    }

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun getImage(ctx: Context, isGallery: Boolean): Job = viewModelScope.launch {
        if (isGallery) {
            resultingActivityHandler.getContent(Constants.IMAGES_MT).also { uri ->
                stateDialog.value = false
                uri?.let { u ->
                    state = state.copy(imgUrl = u.toString())
                    file = ComposeFileProvider.createFileFromUri(ctx, u)
                }
            }
        } else {
            resultingActivityHandler.takePicturePreview().also { bitmap ->
                stateDialog.value = false
                bitmap?.let { b ->
                    state = state.copy(imgUrl = ComposeFileProvider.getPathFromBitmap(ctx, b))
                    file = File(state.imgUrl!!)
                }
            }
        }
    }

    fun update(url: String): Job = viewModelScope.launch(Dispatchers.IO) {
        val userUpdate = User(id = user.id, username = state.username, imgUrl = url)
        updateResponse = Response.Loading
        val result = usersUseCases.update(userUpdate)
        updateResponse = result
    }

    fun uploadImage(): Job = viewModelScope.launch(Dispatchers.IO) {
        file?.let { photo ->
            uploadImageResponse = Response.Loading
            val result = usersUseCases.uploadImage(photo)
            uploadImageResponse = result
        }
    }

    fun validateUsername() {
        if (state.username.length >= 5) {
            isUsernameValid = true
            usernameErrMsg = null
        } else {
            isUsernameValid = false
            usernameErrMsg = "El nombre de usuario debe ser mayor o igual a 5 caracteres."
        }
    }
}