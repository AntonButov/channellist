package com.butovanton.channellist.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.butovanton.chanellist.R
import com.butovanton.channellist.presentation.theme.ChanellistTheme
import java.util.Objects

@Composable
fun ChannelItem(modifier: Modifier = Modifier, name: String, url: String?, icon: String?) {
    ListItem(
        modifier = modifier,
        leadingContent = {
            val imageloader = rememberAsyncImagePainter(model = icon)
            Image(
                contentScale = ContentScale.Crop,
                painter = imageloader,
                contentDescription = name
            )
        },
        headlineContent = {
            Text(text = name)
        },
        trailingContent = {
            Icon(imageVector = Icons.Default.Star, contentDescription = "favorite")
        }
    )
}

@Preview
@Composable
fun ChannelItemPreview() {
    ChanellistTheme {
        ChannelItem(name = "name", url = null, icon = "https:\\/\\/assets-iptv2022.cdnvideo.ru\\/static\\/channel\\/72\\/logo_256_1655448761.png")
    }
}