package com.akirachix.postsapp.api
import com.akirachix.postsapp.model.Comments
import com.akirachix.postsapp.model.PostRequest
import com.akirachix.postsapp.model.Posts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostsApiInterface {
    @GET("/posts")
    suspend fun fetchPosts(): Response<List<Posts>>

    @GET("/posts/{postId}")
    suspend fun fetchPostsById(@Path("postId") postId:Int): Response<Posts>

    @GET("/posts/{postId}/comments")
    suspend fun fetchComments(@Path("postId") postId: Int): Response<List<Comments>>

    @POST("/posts")
    suspend fun createPost(@Body postRequest: PostRequest): Response<Posts>
}
