package com.jayman.fetchexercise.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
) {
    val uiState = viewModel.uiState
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(uiState.listItems.size) { index ->
            val item = uiState.listItems[index]
            ListItem(
                id = item.id,
                name = item.name,
            )
        }
    }
}

@Composable
fun ListItem(
    id: Int,
    name: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = "id: $id",
            )
            Text(
                text = "name: $name",
            )
        }
    }
}