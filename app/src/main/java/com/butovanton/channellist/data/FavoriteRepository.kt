package com.butovanton.channellist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class FavoriteRepository: IFavoriteRepository {
    override fun getFavorites(): Flow<List<String>> {
        return flowOf(emptyList())
    }

    override fun add(name: String) {

    }

    override fun remove(name: String) {

    }
}