package com.butovanton.channellist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            AsyncImage(
                model = icon,
                contentDescription = null
            )
        } ?: Spacer(modifier = Modifier.size(60.dp))
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium
        )
        FavoriteIcon(isChecked = isFavorite, onClick = onFavoriteClick)
    }
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
            isFavorite = false,
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

