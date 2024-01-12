package com.butovanton.channellist.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.butovanton.channellist.presentation.theme.ChannelListTheme

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: TabScreen,
    onTabSelect: (TabScreen) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected.ordinal,
        modifier = modifier,
    ) {
        titles.forEachIndexed { index, text ->

            Tab(
                selected = index == tabSelected.ordinal,
                onClick = { onTabSelect(TabScreen.entries[index]) },
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(Alignment.Center).padding(bottom = 16.dp),
                    text = text,
                )
            }
        }
    }
}

@Preview
@Composable
fun TabsPreview() {
    ChannelListTheme {
        Tabs(
            titles = listOf("All", "Favorite"),
            tabSelected = TabScreen.Favorite,
            onTabSelect = {}
        )
    }
}