package com.nutmeg.philip.arnold.retrofit.data

data class PostData(
    var user: User? = null,
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)