package com.jayman.fetchexercise.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * A screen that displays a list of items grouped and sorted by their list ID and then name.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val listItems = uiState.groupedListItems
    LazyColumn(modifier = modifier.fillMaxSize()) {
        listItems.forEach { (listId, itemNames) ->
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "List ID: $listId"
                    )
                }
            }
            items(itemNames) { itemName ->
                ListItem(
                    name = itemName.name,
                    modifier = Modifier.padding(8.dp),
                    onClick = { viewModel.deleteListItem(listId, itemName.id) }
                )
            }
        }
    }
}

/**
 * Composable that shows a single item.
 */
@Composable
fun ListItem(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        onClick = onClick,
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = "name: $name",
            )
        }
    }
}