package com.example.topredditpost.domain.entity

data class Post (
    val id : String = ID,
    val author : String,
    val img : String ?= null,
    val numComment : String,
    val time : String
){
    companion object{
        const val ID = "0"
    }
}