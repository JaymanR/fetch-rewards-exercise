package com.jayman.fetchexercise.data

import com.jayman.fetchexercise.model.Item
import com.jayman.fetchexercise.network.AppApiService

interface ListDataRepository {
    suspend fun getListData(): List<Item>
}

class NetworkListDataRepository(
    private val apiService: AppApiService
) : ListDataRepository {
    override suspend fun getListData(): List<Item> = apiService.getData()
}