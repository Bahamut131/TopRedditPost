package com.example.topredditpost.data.repository

import com.example.topredditpost.domain.entity.Post
import com.example.topredditpost.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl : PostRepository {

    override fun getListOfPost(): Flow<List<Post>> {
        TODO("Not yet implemented")
    }

    override suspend fun downloadImage() {
        TODO("Not yet implemented")
    }
}