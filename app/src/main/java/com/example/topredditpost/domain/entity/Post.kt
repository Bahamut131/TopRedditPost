package com.example.topredditpost.domain.entity

data class Post (
    val author : String,
    val title : String,
    val img : String ?= null,
    val numComment : Int,
    val time : String
)