package com.nutmeg.philip.arnold.retrofit

import android.annotation.SuppressLint
import com.nutmeg.philip.arnold.mainpage.DataResponseInterface
import com.nutmeg.philip.arnold.mainpage.UserResponseInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException


object RetrofitRxFactory {
    val repoBaseUrl = "https://jsonplaceholder.typicode.com/"
    val repoRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(repoBaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service by lazy {
        repoRetrofit.create(RetrofitRxService::class.java)
    }
    var disposable: Disposable? = null

    @SuppressLint("CheckResult")
    @Throws(IOException::class)
    fun getUsers(responseHandler: UserResponseInterface) {
        service.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                responseHandler.ReturnUsers(results, true)
            }, responseHandler::ReturnError)
    }

    @SuppressLint("CheckResult")
    @Throws(IOException::class)
    fun getPosts(responseHandler: DataResponseInterface) {
        service.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(responseHandler::ReturnData, responseHandler::ReturnError)
    }
}