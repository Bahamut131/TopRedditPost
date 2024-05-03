package com.example.topredditpost.data.network.model

import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("children")
    val children: ArrayList<Children> = arrayListOf(),
)