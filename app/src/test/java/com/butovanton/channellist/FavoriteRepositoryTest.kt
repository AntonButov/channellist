package com.butovanton.channellist

import com.butovanton.channellist.data.FavoriteRepository
import com.butovanton.channellist.data.IFavoriteStorage
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class FavoriteRepositoryTest {

    @Test
    fun `on consisted name do not add`() {
        val favoriteStorage = mockk<IFavoriteStorage>(relaxed = true)
        val favoriteRepository = FavoriteRepository(favoriteStorage)
        favoriteRepository.add("name")
        favoriteRepository.add("name")
        verify(exactly = 1) { favoriteStorage.saveFavorites(any()) }
    }
}