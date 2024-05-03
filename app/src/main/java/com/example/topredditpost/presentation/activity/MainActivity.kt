package com.example.topredditpost.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.topredditpost.R
import com.example.topredditpost.data.network.ApiFactory
import com.example.topredditpost.data.network.ApiService
import com.example.topredditpost.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding?= null
    private val binding : ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val response = ApiFactory.apiService
        scope.launch {
            val result = response.getListOfTopPost()
            Log.d("MyTag","$result")
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}