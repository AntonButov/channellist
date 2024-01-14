package com.butovanton.channellist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteRepository(private val favoriteStorage: IFavoriteStorage) : IFavoriteRepository {

    private val _favorites = MutableStateFlow(favoriteStorage.getFavorites())
    override val favorites: Flow<List<String>> = _favorites.asStateFlow()

    override fun add(name: String) {
        if (name in _favorites.value) return
        val newFavorites = _favorites.value + name
        _favorites.value = newFavorites
        favoriteStorage.saveFavorites(newFavorites)
    }


    override fun remove(name: String) {
        val newFavorites = _favorites.value - name
        _favorites.value = newFavorites
        favoriteStorage.saveFavorites(newFavorites)
    }
}