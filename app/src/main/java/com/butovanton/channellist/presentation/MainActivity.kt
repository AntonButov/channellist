package com.butovanton.channellist.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.butovanton.channellist.PlayerActivity
import com.butovanton.channellist.PlayerActivity.Companion.startPlayerActivity
import com.butovanton.channellist.presentation.components.TabScreen
import com.butovanton.channellist.presentation.theme.ChannelListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChannelListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    TabScreen { url, icon, text ->
                         url?: return@TabScreen
                       this.startPlayerActivity(url, icon ?: "", text)
                    }
                }
            }
        }
    }
}