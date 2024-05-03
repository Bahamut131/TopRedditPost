package com.example.topredditpost.data.network.model

import com.google.gson.annotations.SerializedName

data class JsonTopPost(
    @SerializedName("data" )
    val data : Data?   = Data()
)