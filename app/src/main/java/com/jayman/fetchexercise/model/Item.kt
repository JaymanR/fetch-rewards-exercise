package com.jayman.fetchexercise.model

import kotlinx.serialization.Serializable

/**
 * model for an Item in a List.
 */
@Serializable
data class Item(
    val id: Int,
    val listId: Int,
    val name: String,
)