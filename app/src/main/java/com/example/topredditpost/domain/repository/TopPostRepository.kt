package com.example.topredditpost.domain.repository

import com.example.topredditpost.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface TopPostRepository {

    fun getListOfTopPost() : Flow<List<Post>>

    suspend fun downloadImage()

}