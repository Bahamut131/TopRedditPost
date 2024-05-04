package com.example.topredditpost.data.repository

import com.example.topredditpost.data.mapper.TopPostMapper
import com.example.topredditpost.data.network.ApiService
import com.example.topredditpost.data.network.model.JsonTopPost
import com.example.topredditpost.domain.entity.Post
import com.example.topredditpost.domain.repository.TopPostRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopPostRepositoryImpl @Inject constructor(
    val mapper: TopPostMapper,
    val apiService: ApiService
) : TopPostRepository {

    private var _after : String =""
    private val _listOfPosts = mutableListOf<Post>()

    override fun getListOfTopPost():Flow<List<Post>>  = flow{
        val listDto = apiService.getListOfTopPost(10,_after)
        _after = listDto.data?.after.toString()
        val listDataX = mapper.takeDataXFromJson(listDto)
        val listTopPost = listDataX.map { mapper.mapDtoToEntity(it) }

        _listOfPosts.addAll(listTopPost)
        emit(_listOfPosts.toList())
    }



    override suspend fun downloadImage() {
        TODO("Not yet implemented")
    }
}