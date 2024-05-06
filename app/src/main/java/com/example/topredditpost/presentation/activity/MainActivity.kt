package com.example.topredditpost.presentation.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.topredditpost.R
import com.example.topredditpost.TopPostApp
import com.example.topredditpost.data.network.ApiFactory
import com.example.topredditpost.data.network.ApiService
import com.example.topredditpost.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding?= null
    private val binding : ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private val component by lazy {
        (application as TopPostApp).component
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}