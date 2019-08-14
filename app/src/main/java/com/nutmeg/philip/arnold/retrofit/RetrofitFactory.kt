package com.nutmeg.philip.arnold.retrofit

import com.nutmeg.philip.arnold.mainpage.DataResponseInterface
import com.nutmeg.philip.arnold.mainpage.UserResponseInterface
import com.nutmeg.philip.arnold.retrofit.data.PostData
import com.nutmeg.philip.arnold.retrofit.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    val repoBaseUrl = "https://jsonplaceholder.typicode.com/"
    val repoRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(repoBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service by lazy {
        repoRetrofit.create(RetrofitService::class.java)
    }

    fun getUsers(responseHandler: UserResponseInterface) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = service.getUsers()
                withContext(Dispatchers.Main) {
                    LoadUsers(call, responseHandler)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    responseHandler.ReturnError(e)
                }
            }
        }
    }

    fun getPosts(responseHandler: DataResponseInterface) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = service.getPosts()
                withContext(Dispatchers.Main) {
                    LoadData(call, responseHandler)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    responseHandler.ReturnError(e)
                }
            }
        }
    }

    fun LoadUsers(call: Response<ArrayList<User>>, responseHandler: UserResponseInterface) {
        if (call.isSuccessful) {
            val data = call.body()
            responseHandler.ReturnUsers(data!!, false)
        }
    }
    fun LoadData(call: Response<ArrayList<PostData>>, responseHandler: DataResponseInterface) {
        if (call.isSuccessful) {
            val data = call.body()
            responseHandler.ReturnData(data!!)
        }
    }

}