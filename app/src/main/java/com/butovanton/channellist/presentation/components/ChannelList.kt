package com.butovanton.channellist.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.butovanton.channellist.presentation.ChannelUi
import com.butovanton.channellist.presentation.theme.ChannelListTheme

@Composable
fun ChannelList(
    channels: List<ChannelUi>,
    onFavoriteClick: (String) -> Unit,
    onClick: (String?, String?, String) -> Unit
) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.size(20.dp))
        }
        items(channels.size) { index ->
            val name = channels[index].name
            val icon = channels[index].icon
            val url = channels[index].url
            ChannelItem(
                name = name,
                icon = icon,
                isFavorite = channels[index].isFavorite,
                onFavoriteClick = {
                    onFavoriteClick(channels[index].name)
                },
                onClick = {
                    onClick(url, icon, name)
                }
            )
                Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Preview
@Composable
fun ChannelListPreview() {
    ChannelListTheme {
        ChannelList(
            channels = listOf(
                ChannelUi("name", null, null, true),
                ChannelUi("name", null, null, false)
            ),
            onFavoriteClick = {},
            onClick = { _, _, _ -> },
        )
    }
}