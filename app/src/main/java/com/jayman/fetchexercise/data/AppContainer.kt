package com.jayman.fetchexercise.data

import com.jayman.fetchexercise.network.AppApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dependency Injection container.
 */
interface AppContainer {
    val listDataRepository: ListDataRepository
}

/**
 * Implementation of the Dependency Injection container.
 */
class DefaultAppContainer : AppContainer {

    /**
     * Base URL for the API.
     */
    private val baseUrl = "https://hiring.fetch.com/"

    /**
     * Retrofit instance for making network requests.
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating API calls.
     */
    private val retrofitService: AppApiService by lazy {
        retrofit.create(AppApiService::class.java)
    }

    /**
     * DI implementation for ListDataRepository.
     */
    override val listDataRepository: ListDataRepository by lazy {
        NetworkListDataRepository(retrofitService)
    }
}