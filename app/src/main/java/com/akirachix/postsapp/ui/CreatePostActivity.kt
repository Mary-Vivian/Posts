package com.akirachix.postsapp.ui

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akirachix.postsapp.R
import com.akirachix.postsapp.databinding.ActivityCreatePostBinding
import com.akirachix.postsapp.model.PostRequest
import com.akirachix.postsapp.viewmodel.PostsViewModel

class CreatePostActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreatePostBinding
    val postsViewModel: PostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?. setDisplayShowHomeEnabled(true)
        supportActionBar?. setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)

        }

    override fun onResume() {
        super.onResume()
        binding.btnPost.setOnClickListener {
            validatePost() }
        postsViewModel.createPostLiveData.observe(this){
            message ->
            binding.progressBar.visibility= View.GONE
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            clearForm()

        }
        postsViewModel.errorLiveData.observe(this){
                error ->
            binding.progressBar.visibility= View.GONE
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()

        }

    }
    fun validatePost(){
        val userId = binding.etUserId.text.toString()
        val title = binding.etTitle.text.toString()
        val body = binding.etBody.text.toString()
        var error = false
        if (userId.isBlank()){
            binding.etUserId.error = "User id is required"
            error = true
        }
        if (title.isBlank()){
            binding.etTitle.error = "title is required"
            error = true
        }
        if (body.isBlank()){
            binding.etBody.error = "body is required"
            error = true
        }
        if (!error){
            binding.progressBar.visibility = View.GONE
            val postRequest = PostRequest(userId=userId.toInt(), title = title, body= body)
            postsViewModel.creatPost(postRequest)
        }


    }
    fun clearForm(){
        binding.etUserId.text.clear()
        binding.etTitle.text.clear()
        binding.etBody.text.clear()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


    }
