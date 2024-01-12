package com.butovanton.channellist.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.butovanton.chanellist.R
import com.butovanton.channellist.presentation.ChannelUi
import com.butovanton.channellist.presentation.theme.ChannelListTheme

enum class TabScreen(@StringRes val nameId: Int) {
    All(nameId = R.string.all), Favorite(R.string.favorite)
}

@Composable
fun TabScreen(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    selectedTabScreen: TabScreen,
    onTabSelect: (TabScreen) -> Unit,
    channels: List<ChannelUi>,
    onFavoriteClick: (String) -> Unit,
    onClick: (String?) -> Unit,
    placeHolderText: String = "Канал"
) {
    Column {
        Search(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 24.dp),
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            placeHolderText = placeHolderText
        )
        Spacer(modifier = Modifier.size(12.dp))
        Tabs(
            titles = TabScreen.entries.map { "1" },
            tabSelected = selectedTabScreen,
            onTabSelect = onTabSelect
        )
        Spacer(modifier = Modifier.size(6.dp))
        Divider(thickness = 2.dp)
        Spacer(modifier = Modifier.size(20.dp))
        ChannelList(
            channels = channels,
            onFavoriteClick = onFavoriteClick,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
fun TabScreenPreview() {
    ChannelListTheme {
        TabScreen(
            searchQuery = "Канал",
            onSearchQueryChanged = {},
            selectedTabScreen = TabScreen.All,
            onTabSelect = {},
            channels = listOf(
                ChannelUi("name", null, null, true),
                ChannelUi("name", null, null, false)
            ),
            onFavoriteClick = {},
            onClick = {}
        )
    }
}

