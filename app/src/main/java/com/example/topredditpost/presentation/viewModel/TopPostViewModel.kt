package com.example.topredditpost.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topredditpost.domain.entity.Post
import com.example.topredditpost.domain.useCase.GetListOfTopPostUseCase
import com.example.topredditpost.domain.useCase.ImageDownloadUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopPostViewModel @Inject constructor(
    val getListOfTopPostUseCase: GetListOfTopPostUseCase,
    val imageDownloadUseCase: ImageDownloadUseCase
) : ViewModel() {

    private val _list = MutableLiveData<List<Post>>()
    val list: LiveData<List<Post>> = _list

    init {
        loadTopPost()
    }

    fun loadMorePosts() {
        loadTopPost()
    }

    private fun loadTopPost() {
        viewModelScope.launch {
            getListOfTopPostUseCase.invoke().collect { posts ->
                _list.postValue(posts)
            }
        }
    }

     fun downloadImage(url : String){
        viewModelScope.launch {
            imageDownloadUseCase.invoke(url)
        }
    }

}