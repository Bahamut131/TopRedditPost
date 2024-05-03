package com.example.topredditpost.data.mapper

import com.example.topredditpost.data.network.model.Data
import com.example.topredditpost.data.network.model.DataX
import com.example.topredditpost.domain.entity.Post

class TopPostMapper {

    fun mapDtoToDbModel(dto: DataX) = Post(
        id = dto.id,
        author = dto.subreddit,
        time = convertUtcToTimeString(dto.createdUtc.toLong()),
        numComment = dto.numComments.toString(),
        img = BASE_IMG_URL + dto.thumbnail
    )


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

    companion object{
        const val BASE_IMG_URL = "https://b.thumbs.redditmedia.com/"
    }

}