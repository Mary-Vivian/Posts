package com.akirachix.postsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.postsapp.model.Comments
import com.akirachix.postsapp.model.PostRequest
import com.akirachix.postsapp.repository.PostsRepository
import com.akirachix.postsapp.model.Posts
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    val postsRepo = PostsRepository()
    val errorLiveData = MutableLiveData<String>()
    val postsLiveData = MutableLiveData<List<Posts>>()
    val postLiveData = MutableLiveData<Posts>()
    val commentsLiveData = MutableLiveData<List<Comments>>()
    val createPostLiveData = MutableLiveData<String>()

    fun fetchPosts(){
        viewModelScope.launch {
            val response = postsRepo.fetchPosts()
            if (response.isSuccessful){
                postsLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }

    }

    fun fetchComments(postId: Int) {
        viewModelScope.launch {
            var response = postsRepo.fetchComments(postId)
            if (response.isSuccessful){
                commentsLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }

    }
    fun creatPost(postRequest: PostRequest){
        viewModelScope.launch {
            var response = postsRepo.createPost(postRequest)
            if (response.isSuccessful){
                createPostLiveData.postValue("Post created successfully")
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}