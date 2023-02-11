package com.edival.recioblog.presentation.screens.posts_edit

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.recioblog.R
import com.edival.recioblog.core.Constants
import com.edival.recioblog.domain.model.CategoryRadioButton
import com.edival.recioblog.domain.model.Post
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.use_cases.posts.PostsUseCases
import com.edival.recioblog.presentation.utils.ComposeFileProvider
import com.edival.recioblog.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PostEditViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(PostEditState())
        private set
    private var _isNameValid: Boolean by mutableStateOf(false)
    var nameErrMsg: String? by mutableStateOf(null)
        private set
    private var _isDescriptionValid: Boolean by mutableStateOf(false)
    var descErrMsg: String? by mutableStateOf(null)
    var isEnabledEditPostButton: Boolean = false
    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
    var stateDialog: MutableState<Boolean> = mutableStateOf(false)
    val resultingActivityHandler = ResultingActivityHandler()
    private var file: File? = null
    private var post = Post()
    val radioOptions = listOf(
        CategoryRadioButton(R.string.pc, R.drawable.outline_computer),
        CategoryRadioButton(R.string.ps4, R.drawable.play_station_logo),
        CategoryRadioButton(R.string.xbox, R.drawable.xbox_logo),
        CategoryRadioButton(R.string.nintendo, R.drawable.nintendo_logo),
        CategoryRadioButton(R.string.mobile_phone, R.drawable.outline_phone_android)
    )

    init {
        savedStateHandle.get<String>(Constants.POST_ARG)?.let { userStr ->
            post = Post.fromJson(userStr)
            state = state.copy(
                imgUrl = post.imgUrl,
                name = post.name ?: "",
                description = post.description ?: "",
                category = post.category ?: ""
            )
        }
    }

    fun updatePost(): Job = viewModelScope.launch {
        val postUpdate = Post(
            id = post.id,
            name = state.name,
            description = state.description,
            imgUrl = post.imgUrl,
            category = state.category
        )
        updatePostResponse = Response.Loading
        val result = postsUseCases.updatePost(postUpdate, file)
        updatePostResponse = result
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

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onDescriptionInput(desc: String) {
        state = state.copy(description = desc)
    }

    fun onCategoryInput(ctg: String) {
        state = state.copy(category = ctg)
    }

    fun validateName(ctx: Context) {
        if (state.name.isNotBlank()) {
            _isNameValid = true
            nameErrMsg = null
        } else {
            _isNameValid = false
            nameErrMsg = ctx.getString(R.string.name_err_msg)
        }
        isEnabledEditPostButton = _isNameValid && _isDescriptionValid
    }

    fun validateDesc(ctx: Context) {
        if (state.description.isNotBlank()) {
            _isDescriptionValid = true
            descErrMsg = null
        } else {
            _isDescriptionValid = false
            descErrMsg = ctx.getString(R.string.desc_err_msg)
        }
        isEnabledEditPostButton = _isNameValid && _isDescriptionValid
    }
}