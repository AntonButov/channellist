package com.butovanton.channellist.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.butovanton.channellist.domain.Channel
import com.butovanton.channellist.domain.IRepository
import com.butovanton.channellist.presentation.components.TabScreen
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.zip

private const val SEARCH_QUERY = "searchQuery"

class TabsViewModel(
    repository: IRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    private val _tab: MutableStateFlow<TabScreen> = MutableStateFlow(TabScreen.All)
    val tab: StateFlow<TabScreen> = _tab.asStateFlow()

    @OptIn(FlowPreview::class)
    val channels: Flow<List<ChannelUi>?> = repository.getChannels().mapNotNull {
        it?.map {
            ChannelUi(it.name, it.url, it.icon, false)
        }
    }.zip(searchQuery.debounce(1000)) { channels, query ->
        if (query.isEmpty()) {
            channels
        } else {
            channels.filter { it.name.contains(query, ignoreCase = true) }
        }
    }.zip(tab) {
        channels, tab ->
        when (tab) {
            TabScreen.All -> channels
            TabScreen.Favorite -> channels.filter { it.isFavorite }
        }
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onTabSelect(tab: TabScreen) {
        _tab.value = tab
    }


}