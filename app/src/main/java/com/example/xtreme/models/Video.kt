package com.example.xtreme.models

data class Video(
    val id: Int,
    val image: Int,
    val isNew: Boolean,
    //val rating: String,
    //val likePercent: Int,
    //val voteCount: Int,
    val title:String,
    val language:String,
    val type: String
)