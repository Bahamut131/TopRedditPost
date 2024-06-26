package com.example.topredditpost.data.network

import com.example.topredditpost.data.network.model.DataX
import com.example.topredditpost.data.network.model.JsonTopPost
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface ApiService {
    @GET("top.json")
    suspend fun getListOfTopPost(
        @Query(QUERY_LIMIT) limit : Int = 2,
        @Query(QUERY_AFTER) after : String?
    ) : JsonTopPost


    companion object{
        const val QUERY_LIMIT = "limit"
        const val QUERY_AFTER = "after"
    }
}