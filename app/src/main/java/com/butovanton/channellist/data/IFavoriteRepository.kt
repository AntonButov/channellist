package com.butovanton.channellist.data

import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {

    val favorites: Flow<List<String>>

    fun add(name: String)

    fun remove(name: String)
}