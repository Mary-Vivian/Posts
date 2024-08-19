package com.akirachix.postsapp.repository

import com.akirachix.postsapp.api.ApiClient
import com.akirachix.postsapp.api.PostsApiInterface
import com.akirachix.postsapp.model.Comments
import com.akirachix.postsapp.model.PostRequest
import com.akirachix.postsapp.model.Posts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response



class PostsRepository {
    val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)

    suspend fun fetchPosts(): Response<List<Posts>>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPosts()
        }
    }

    suspend fun fetchPostsById(postId: Int): Response<Posts>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPostsById(postId)
        }
    }

    suspend fun fetchComments(postId: Int): Response<List<Comments>>{
        return withContext(Dispatchers.IO){
            apiClient.fetchComments(postId)
        }
    }
    suspend fun createPost(postRequest: PostRequest):Response<Posts>{
        return withContext(Dispatchers.IO){
            apiClient.createPost(postRequest)
        }
    }
}