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
    onClick: (String?) -> Unit
) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.size(20.dp))
        }
        items(channels.size) { index ->
            ChannelItem(
                name = channels[index].name,
                icon = channels[index].icon,
                isFavorite = channels[index].isFavorite,
                onFavoriteClick = {
                    onFavoriteClick(channels[index].name)
                },
                onClick = {
                    onClick(channels[index].url)
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
            onClick = {}
        )
    }
}