package com.butovanton.channellist

import androidx.lifecycle.SavedStateHandle
import com.butovanton.channellist.domain.IRepository
import com.butovanton.channellist.presentation.TabsViewModel
import com.butovanton.channellist.presentation.components.TabScreen
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ViewModelTest {

    private val repository
        get() = mockk<IRepository>()
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


}