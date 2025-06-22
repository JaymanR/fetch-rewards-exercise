package com.jayman.fetchexercise.data

import com.jayman.fetchexercise.model.Item
import com.jayman.fetchexercise.network.AppApiService

/**
 * Repo that fetches data from API service.
 */
interface ListDataRepository {
    suspend fun getListData(): List<Item>
}

/**
 * Network implementation of [ListDataRepository].
 */
class NetworkListDataRepository(
    private val apiService: AppApiService
) : ListDataRepository {
    override suspend fun getListData(): List<Item> = apiService.getData()
}