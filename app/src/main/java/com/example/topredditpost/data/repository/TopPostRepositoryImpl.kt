package com.example.topredditpost.data.repository

import com.example.topredditpost.domain.entity.Post
import com.example.topredditpost.domain.repository.TopPostRepository
import kotlinx.coroutines.flow.Flow

class TopPostRepositoryImpl : TopPostRepository {

    override fun getListOfTopPost(): Flow<List<Post>> {
        TODO("Not yet implemented")
    }

    override suspend fun downloadImage() {
        TODO("Not yet implemented")
    }
}