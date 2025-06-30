package com.jayman.fetchexercise.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jayman.fetchexercise.Application
import com.jayman.fetchexercise.data.ListDataRepository
import com.jayman.fetchexercise.model.Item
import kotlinx.coroutines.launch

private const val TAG = "AppViewModel"

/**
 * ViewModel for the app.
 */
class AppViewModel(
    private val listDataRepository: ListDataRepository
) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set


    init {
        getListData()
    }

    /**
     * Fetches the list data and sorts it.
     */
    fun getListData() {
        viewModelScope.launch {
            val items = try {
                val data = listDataRepository
                    .getListData()
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy({ it.listId }, { it.name }))
                Log.d(TAG, "received $data.size")
                data.groupBy { it.listId }
            } catch (e: Exception) {
                Log.e(TAG, "error: $e")
                emptyMap()
            }
            uiState = uiState.copy(items)
        }
    }

    fun deleteListItem(listId: Int, id: Int) {
        val mutableMap = uiState.groupedListItems.toMutableMap()
        val list = mutableMap[listId]?.toMutableList()
        list?.removeIf { it.id == id }

        if (list != null) { mutableMap[listId] = list }

        uiState = uiState.copy(mutableMap)
    }

    data class UiState(
        var groupedListItems: Map<Int, List<Item>> = emptyMap(),
    )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                val listDataRepository = application.container.listDataRepository
                AppViewModel(listDataRepository = listDataRepository)
            }
        }
    }
}