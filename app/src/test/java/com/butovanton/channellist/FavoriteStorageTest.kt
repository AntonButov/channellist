package com.butovanton.channellist

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.butovanton.channellist.data.FavoriteStorage
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FavoriteStorageTest {

    @Test
    fun `on getFavorites should return saved list of favorites`() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        assertNotNull(context)
        val sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        val gson = Gson()
        val favoriteStorage = FavoriteStorage(sharedPreferences, gson)
        val favorites = listOf("first", "second")
        favoriteStorage.saveFavorites(favorites)
        val savedFavorites = favoriteStorage.getFavorites()
        assertEquals(savedFavorites, favorites)
    }
}