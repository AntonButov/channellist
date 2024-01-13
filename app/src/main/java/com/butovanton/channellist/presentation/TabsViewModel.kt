package com.butovanton.channellist.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.butovanton.channellist.data.IFavoriteRepository
import com.butovanton.channellist.domain.IRepository
import com.butovanton.channellist.presentation.components.TabScreen
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapNotNull

private const val SEARCH_QUERY = "searchQuery"

class TabsViewModel(
    repository: IRepository,
    private val favoriteRepository: IFavoriteRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    private val _tab: MutableStateFlow<TabScreen> = MutableStateFlow(TabScreen.All)
    val tab: StateFlow<TabScreen> = _tab.asStateFlow()

    val channels: Flow<List<ChannelUi>> =
        repository
            .getChannels()
            .mapNotNull { it }
            .combine(favoriteRepository.getFavorites()) { channels, favorites ->
                channels.map {
                    ChannelUi(it.name, it.url, it.icon, favorites.contains(it.name))
                }
            }.combine(searchQuery) { channels, query ->
                if (query.isEmpty()) {
                    channels
                } else {
                    channels.filter { it.name.contains(query, ignoreCase = true) }
                }
            }.combine(tab) { channels, tab ->
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

    fun onFavoriteClick(name: String) {
        when (tab.value) {
            TabScreen.All -> favoriteRepository.add(name)
            TabScreen.Favorite -> favoriteRepository.remove(name)
        }
    }

}