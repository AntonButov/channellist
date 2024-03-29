package com.butovanton.channellist

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.butovanton.channellist.data.FavoriteStorage
import com.google.gson.Gson
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.stopKoin
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FavoriteStorageTest {

    @After
    fun tearDown() {
        stopKoin()
    }

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
        sharedPreferences.edit().clear().apply()
    }

    @Test
    fun `save some favorite`() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val sharedPreferences = context.getSharedPreferences("testSecond", Context.MODE_PRIVATE)
        val gson = Gson()
        val favoriteStorage = FavoriteStorage(sharedPreferences, gson)
        val favorites = listOf("some", "some")
        favoriteStorage.saveFavorites(favorites)
        val savedFavorites = favoriteStorage.getFavorites()
        assertEquals(savedFavorites.count(), 2)
        sharedPreferences.edit().clear().apply()
    }
}