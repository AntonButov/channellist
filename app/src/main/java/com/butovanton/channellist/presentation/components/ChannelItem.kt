package com.butovanton.channellist.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.butovanton.chanellist.R

@Composable
fun ChannelItem(
    modifier: Modifier = Modifier,
    name: String,
    icon: String?,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        leadingContent = {
            AsyncImage(
                model = icon,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
        },
        headlineContent = {
            Text(text = name)
        },
        trailingContent = {
            FavoriteIcon(isChecked = isFavorite, onClick = onFavoriteClick)
        }
    )
}

@Preview()
@Composable
fun ChannelItemPreview() {
    MaterialTheme {
        ChannelItem(
            name = "name",
            isFavorite = true,
            onFavoriteClick = {},
            icon = null,
            onClick = {}
        )
    }
}

@Composable
fun FavoriteIcon(isChecked: Boolean, onClick: () -> Unit) { // todo
    FilledIconButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Star, contentDescription = null)
    }
}

