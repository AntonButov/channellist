package com.butovanton.channellist.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.butovanton.channellist.presentation.theme.ChannelListTheme

@Composable
fun ChannelItem(
    modifier: Modifier = Modifier,
    name: String,
    icon: String?,
    isFavorite: Boolean?,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .clip(MaterialTheme.shapes.medium),
        leadingContent = {
            icon?.let {
                AsyncImage(
                    model = icon,
                    contentDescription = null
                )
            } ?: Spacer(modifier = Modifier.size(60.dp))
        },
        headlineContent = {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )
        },
        trailingContent = {
            isFavorite?.let {
                FavoriteIcon(isChecked = isFavorite, onClick = onFavoriteClick)
            }
        }
    )
}

@Preview()
@Composable
fun ChannelItemPreviewChecked() {
    ChannelListTheme {
        ChannelItem(
            name = "name",
            isFavorite = true,
            onFavoriteClick = {},
            icon = null,
            onClick = {}
        )
    }
}

@Preview()
@Composable
fun ChannelItemPreview() {
    ChannelListTheme {
        ChannelItem(
            name = "name",
            isFavorite = null,
            onFavoriteClick = {},
            icon = null,
            onClick = {}
        )
    }
}

@Composable
fun FavoriteIcon(isChecked: Boolean, onClick: () -> Unit) { // todo
    IconButton(onClick = onClick) {
            Icon(
                modifier = Modifier,
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (isChecked) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.tertiary,
            )
    }
}

