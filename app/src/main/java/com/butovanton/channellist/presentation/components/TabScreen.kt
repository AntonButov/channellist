package com.butovanton.channellist.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.butovanton.chanellist.R
import com.butovanton.channellist.presentation.ChannelUi
import com.butovanton.channellist.presentation.TabsViewModel
import com.butovanton.channellist.presentation.theme.ChannelListTheme
import org.koin.androidx.compose.koinViewModel

enum class TabScreen(@StringRes val nameId: Int) {
    All(nameId = R.string.all), Favorite(R.string.favorite)
}

@Composable
fun TabScreen(
    viewModel: TabsViewModel = koinViewModel(),
    play: (String?, String?, String) -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val channels by viewModel.channels.collectAsState(emptyList())
    val selectedTabScreen by viewModel.tab.collectAsState()
    val tabTitles = TabScreen.entries.map { stringResource(id = it.nameId) }
    TabScreen(
        placeHolderText = stringResource(id = R.string.search),
        searchQuery = searchQuery,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        tabTitles = tabTitles,
        selectedTabScreen = selectedTabScreen,
        onTabSelect = viewModel::onTabSelect,
        channels = channels,
        onFavoriteClick = viewModel::onFavoriteClick,
        onClick = play
    )
}

@Composable
private fun TabScreen(
    placeHolderText: String,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    tabTitles: List<String>,
    selectedTabScreen: TabScreen,
    onTabSelect: (TabScreen) -> Unit,
    channels: List<ChannelUi>,
    onFavoriteClick: (String) -> Unit,
    onClick: (String?, String?, String) -> Unit,
) {
    Column {
        Search(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(vertical = 8.dp, horizontal = 24.dp),
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            placeHolderText = placeHolderText
        )
        Spacer(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .height(12.dp)
        )
        Tabs(
            titles = tabTitles,
            tabSelected = selectedTabScreen,
            onTabSelect = onTabSelect
        )
        Spacer(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .height(6.dp)
        )
        Divider(thickness = 2.dp)
        ChannelList(
            channels = channels,
            onFavoriteClick = onFavoriteClick,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
private fun TabScreenPreview() {
    ChannelListTheme {
        TabScreen(
            placeHolderText = "Search",
            searchQuery = "",
            onSearchQueryChanged = {},
            tabTitles = listOf("All", "Favorite"),
            selectedTabScreen = TabScreen.All,
            onTabSelect = {},
            channels = listOf(
                ChannelUi("name", null, null, true),
                ChannelUi("name", null, null, false)
            ),
            onFavoriteClick = {},
            onClick = { _, _, _ -> },
        )
    }
}

