package com.butovanton.channellist.data

import android.content.SharedPreferences
import com.google.gson.Gson

private const val FAVORITE_KEY = "favorite"

class FavoriteStorage(
    private val sharedPreferences: SharedPreferences, private val gson: Gson
) : IFavoriteStorage {

    override fun getFavorites(): List<String> {
        return sharedPreferences.getString(FAVORITE_KEY, null)?.let {
            gson.fromJson(it, Wrapper::class.java).favorites
        } ?: emptyList()
    }

    override fun saveFavorites(favorites: List<String>) {
        val jsonString = gson.toJson(Wrapper(favorites))
        sharedPreferences.edit().putString(FAVORITE_KEY, jsonString).apply()
    }

    private class Wrapper(
        val favorites: List<String>
    )
}