package com.example.topredditpost.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    @Expose
    val data: DataX? =null
)