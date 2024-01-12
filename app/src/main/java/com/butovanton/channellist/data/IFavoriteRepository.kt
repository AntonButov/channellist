package com.butovanton.channellist.data

interface IFavoriteRepository {

    fun isFavorite(name: String): Boolean

    fun add(name: String)

    fun remove(name: String)
}