package com.jayman.fetchexercise.network

import com.jayman.fetchexercise.model.Item
import retrofit2.http.GET

/**
 * public interface that exposes the [getData] method
 */
interface AppApiService {

    /**
     * Returns a [List] of [Item].
     */
    @GET("hiring.json")
    suspend fun getData(): List<Item>
}