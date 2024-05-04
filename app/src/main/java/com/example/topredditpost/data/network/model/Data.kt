package com.example.topredditpost.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Data(

    @SerializedName("after")
    val after: String? = null,
    @SerializedName("dist")
    val dist: Int? = null,
    @SerializedName("modhash")
    val modhash: String? = null,
    @SerializedName("geo_filter")
    val geoFilter: String? = null,
    @SerializedName("before")
    val before: String? = null,
    @SerializedName("children")
    @Expose
    val children: ArrayList<Children>? = null
)