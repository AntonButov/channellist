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

    @Test
    fun `on init have empty list of channels`() {
        val viewModel = TabsViewModel(mockk<IRepository>(relaxed = true), savedStateHandle = SavedStateHandle())
        assertEquals(viewModel.searchQuery.value, "")
    }

    @Test
    fun `on init have started tab`() {
        val viewModel = TabsViewModel(mockk<IRepository>(relaxed = true), savedStateHandle = SavedStateHandle())
        assertEquals(viewModel.tab.value, TabScreen.All)
    }

    @Test
    fun `on init should return some list`() = runBlocking {
        val repository = mockk<IRepository>()
        coEvery { repository.getChannels() } returns flowOf(listOf(Channel("name", null, null)))
        val viewModel = TabsViewModel(repository, savedStateHandle = SavedStateHandle())
        val result = viewModel.channels.first()
        assertEquals(result?.first()?.name, "name")
        assertEquals(result?.count(), 1)
    }

    @Test
    fun `on search 'na' should return the chanel`() = runBlocking {
        val repository = mockk<IRepository>()
        coEvery { repository.getChannels() }
        coEvery { repository.getChannels() } returns flowOf(
            listOf(
                Channel("smart", null, null),
                Channel("star", null, null)
            )
        )
        val viewModel = TabsViewModel(repository, savedStateHandle = SavedStateHandle())
        viewModel.onSearchQueryChanged("st")
        val result = viewModel.channels.first()
        assertEquals(result?.first()?.name, "star")
        assertEquals(result?.count(), 1)

        viewModel.onSearchQueryChanged("")
        val result2 = viewModel.channels.first()
        assertEquals(result2?.count(), 2)
    }


}