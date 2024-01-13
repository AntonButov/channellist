package com.butovanton.channellist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class FavoriteRepository: IFavoriteRepository {
    override fun getFavorites(): Flow<List<String>> {
        return emptyFlow()
    }

    override fun add(name: String) {

    }

    override fun remove(name: String) {

    }
}