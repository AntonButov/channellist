package com.butovanton.channellist.data

interface IFavoriteStorage {

    fun getFavorites(): List<String>

    fun saveFavorites(favorites: List<String>)
}