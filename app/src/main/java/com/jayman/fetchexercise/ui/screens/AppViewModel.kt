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

class AppViewModel(
    private val listDataRepository: ListDataRepository
) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set


    init {
        getListData()
    }

    fun getListData() {
        viewModelScope.launch {
            val items = try {
                val data = listDataRepository.getListData().filter { !it.name.isNullOrBlank() }
                Log.d(TAG, "received $data.size")
                data
            } catch (e: Exception) {
                Log.e(TAG, "error: $e")
                emptyList()
            }
            uiState = uiState.copy(listItems = items)
        }
    }

    data class UiState(
        var listItems: List<Item> = emptyList(),
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