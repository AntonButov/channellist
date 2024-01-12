package com.butovanton.channellist.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.butovanton.channellist.domain.IRepository
import com.butovanton.channellist.presentation.components.TabScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val SEARCH_QUERY = "searchQuery"

class TabsViewModel(
    repository: IRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    private val _tab: MutableStateFlow<TabScreen> = MutableStateFlow(TabScreen.All)
    val tab: StateFlow<TabScreen> = _tab.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onTabSelect(tab: TabScreen) {
        _tab.value = tab
    }

}