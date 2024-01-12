package com.butovanton.channellist

import androidx.lifecycle.SavedStateHandle
import com.butovanton.channellist.domain.Channel
import com.butovanton.channellist.domain.IRepository
import com.butovanton.channellist.presentation.TabsViewModel
import com.butovanton.channellist.presentation.components.TabScreen
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ViewModelTest {

    private val repository = mockk<IRepository>(relaxed = true)
    private val viewModel
        get() = TabsViewModel(repository, savedStateHandle = SavedStateHandle())

    @Test
    fun `on init have empty list of channels`() {
        assertEquals(viewModel.searchQuery.value, "")
    }

    @Test
    fun `on init have started tab`() {
        assertEquals(viewModel.tab.value, TabScreen.All)
    }

    @Test
    fun `on init should return some list`() = runBlocking {
        coEvery { repository.getChannels() } returns flowOf(listOf(Channel("name", null, null)))
        val result = viewModel.channels.first()
        assertEquals(result?.first()?.name, "name")
        assertEquals(result?.count(), 1)
    }


}