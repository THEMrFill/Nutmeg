package com.nutmeg.philip.arnold.retrofit

import com.nutmeg.philip.arnold.retrofit.data.PostData
import com.nutmeg.philip.arnold.retrofit.data.User
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("posts")
    suspend fun getPosts(): Response<ArrayList<PostData>>

    @GET("users")
    suspend fun getUsers() : Response<ArrayList<User>>
}