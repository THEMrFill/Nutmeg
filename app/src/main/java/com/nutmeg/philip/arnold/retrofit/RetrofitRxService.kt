package com.nutmeg.philip.arnold.retrofit

import com.nutmeg.philip.arnold.retrofit.data.PostData
import com.nutmeg.philip.arnold.retrofit.data.User
import io.reactivex.Flowable
import retrofit2.http.GET

interface RetrofitRxService {
    @GET("posts")
    fun getPosts() : Flowable<ArrayList<PostData>>

    @GET("users")
    fun getUsers() : Flowable<ArrayList<User>>
}