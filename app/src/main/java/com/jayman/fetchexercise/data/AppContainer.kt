package com.jayman.fetchexercise.data

import com.jayman.fetchexercise.network.AppApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer {
    val listDataRepository: ListDataRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://hiring.fetch.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AppApiService by lazy {
        retrofit.create(AppApiService::class.java)
    }

    override val listDataRepository: ListDataRepository by lazy {
        NetworkListDataRepository(retrofitService)
    }
}