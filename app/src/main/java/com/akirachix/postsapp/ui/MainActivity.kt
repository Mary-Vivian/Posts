package com.akirachix.postsapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.akirachix.postsapp.databinding.ActivityMainBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.postsapp.model.Posts
import com.akirachix.postsapp.viewmodel.PostsViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val postsViewModel: PostsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postsViewModel.fetchPosts()
//        fetchPosts()
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

//    posts repo only does call apis

    override fun onResume() {
        super.onResume()
        postsViewModel.postsLiveData.observe(this, Observer { postsList->
            displayPosts(postsList)

        })
        binding.fabAddPost.setOnClickListener{
            startActivity(Intent(this, CreatePostActivity::class.java))
        }

        postsViewModel.errorLiveData.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }

//    override fun onCreate() {
//        super.onCreate()
//        Log.d(TAG, "onCreate")
//    }

    override fun onStart(){
        super.onStart()

    }

    //    override fun onResume(){
//        super.onResume()
//        Log.d(TAG, "onResume")
//    }
    override fun onPause() {
        super.onPause()

    }
    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    fun displayPosts(posts: List<Posts>){
        val postsAdapter = PostsAdapter(posts, this)
        binding.rvPosts.adapter = postsAdapter
    }

}







