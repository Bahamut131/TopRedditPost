package com.example.topredditpost.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataX(

    @SerializedName("author_fullname")
    @Expose
    val authorFullname: String,
    @SerializedName("subreddit")
    @Expose
    val subreddit: String,
    @SerializedName("thumbnail_height")
    @Expose
    val thumbnailHeight: Int? = null,
    @SerializedName("thumbnail_width")
    @Expose
    val thumbnailWidth: Int? = null,
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String? = null,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("num_comments")
    @Expose
    val numComments: Int,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("created_utc")
    @Expose
    val createdUtc: Int,
)