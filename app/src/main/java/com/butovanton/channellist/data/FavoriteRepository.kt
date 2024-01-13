package com.butovanton.channellist.data

class FavoriteRepository: IFavoriteRepository {
    override fun isFavorite(name: String): Boolean {
        return true
    }

    override fun add(name: String) {

    }

    override fun remove(name: String) {

    }
}