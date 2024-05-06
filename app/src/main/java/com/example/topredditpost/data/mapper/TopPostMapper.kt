package com.example.topredditpost.data.mapper

import com.example.topredditpost.data.network.model.Data
import com.example.topredditpost.data.network.model.DataX
import com.example.topredditpost.data.network.model.JsonTopPost
import com.example.topredditpost.domain.entity.Post
import javax.inject.Inject

class TopPostMapper @Inject constructor() {

    fun mapDtoToEntity(dto: DataX) = Post(
        id = dto.authorFullname,
        author = dto.subreddit,
        time = convertUtcToTimeString(dto.createdUtc.toLong()),
        numComment = dto.numComments.toString(),
        img = dto.thumbnail,
        fullImage = dto.urlOverriddenByDest
    )


    fun takeDataXFromJson(jsonTopPost: JsonTopPost) : List<DataX>{
        var resultData = mutableListOf<DataX>()
        val data = jsonTopPost.data ?: return resultData
        val children = data.children
        children?.map { resultData.add(it.data) }
        return resultData.toList()
    }

    fun convertUtcToTimeString(utcTimestamp: Long): String {
        val currentMillis = System.currentTimeMillis()
        val timestampMillis = utcTimestamp * 1000
        val diffMillis = currentMillis - timestampMillis

        val seconds = diffMillis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            hours < 1 -> "$minutes minutes ago"
            hours < 24 -> "$hours hours ago"
            else -> "$days day ago"
        }
    }


}