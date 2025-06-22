package com.jayman.fetchexercise.network

import com.jayman.fetchexercise.model.Item
import retrofit2.http.GET

interface AppApiService {

    @GET("hiring.json")
    suspend fun getData(): List<Item>
}