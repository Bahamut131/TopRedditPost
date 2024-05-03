package com.example.topredditpost.data.network.model

import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("subreddit")
    val subreddit: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Int? = null,
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Int? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("num_comments")
    val numComments: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("created_utc")
    val createdUtc: Int? = null,
)