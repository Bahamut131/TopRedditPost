package com.example.topredditpost.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("children")
    @Expose
    val children: ArrayList<Children> ?= null
)