package com.akirachix.postsapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import com.akirachix.postsapp.model.Comments
import com.akirachix.postsapp.api.PostsApiInterface
import com.akirachix.postsapp.databinding.ActivityCommentsBinding
import com.akirachix.postsapp.model.Posts
import com.akirachix.postsapp.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.akirachix.postsapp.viewmodel.PostsViewModel


class CommentsActivity : AppCompatActivity() {
    private var postId = 0
    val postsViewModel : PostsViewModel by viewModels()
    private lateinit var binding: ActivityCommentsBinding
    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if (extra != null) {
            postId = extra.getInt("POST_ID")
            postsViewModel.fetchPosts()
            postsViewModel.fetchComments(postId)

        }
        binding.rvComments.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        postsViewModel.postLiveData.observe(this){ post ->
            binding.tvPostTitle.text = post?.tittle
            binding.tvPostBody.text = post?.body
        }

        postsViewModel.errorLiveData.observe(this, Observer {
                error -> Toast.makeText(this@CommentsActivity, error, Toast.LENGTH_LONG).show()
        })
        postsViewModel.commentsLiveData.observe(this, Observer {commentsList ->
            displayComments(commentsList)
        })
    }
    fun displayComments(commentsList : List<Comments>){
        binding.rvComments.adapter = CommentsAdapter(commentsList)
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()

    }
    }