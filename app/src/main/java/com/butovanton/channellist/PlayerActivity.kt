package com.butovanton.channellist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem.fromUri
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.butovanton.channellist.presentation.theme.ChannelListTheme

private const val CHANNEL_URL = "chanel_url"
private const val CHANEL_ICON = "chanel_icon"
private const val CHANEL_NAME = "chanel_name"

class PlayerActivity : ComponentActivity() {
    @kotlin.OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra(CHANNEL_URL) ?: throw Exception("No url")
        val icon = intent.getStringExtra(CHANEL_ICON) ?: ""
        val text = intent.getStringExtra(CHANEL_NAME) ?: ""
        setContent {
            ChannelListTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                titleContentColor = MaterialTheme.colorScheme.onSurface,
                            ),
                            title = {
                                Row(
                                    modifier = Modifier.padding(all = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        modifier = Modifier.size(44.dp),
                                        model = icon,
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(24.dp))
                                    Text(
                                        text = text
                                    )
                                }
                            },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }

                        )
                    },
                ) { _ ->
                    Player(url = url)
                }
            }
        }
    }

    companion object {
        fun Context.startPlayerActivity(url: String, icon: String, text: String) {
            startActivity(
                Intent(this, PlayerActivity::class.java)
                    .apply {
                        putExtra(CHANNEL_URL, url)
                        putExtra(CHANEL_ICON, icon)
                        putExtra(CHANEL_NAME, text)
                    }
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun Player(modifier: Modifier = Modifier, url: String) {
    val hardCodedUrl =
        Uri.parse("https://albportal.net/albkanalemusic.m3u8") // todo remove when fix backend
    val context = LocalContext.current
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    val player: ExoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                setMediaItem(fromUri(hardCodedUrl))
                playWhenReady = true
                prepare()
            }
    }

    AndroidView(
        modifier = modifier,
        factory = {
            PlayerView(context).apply {
                this.player = player
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            }
        }
    )

    DisposableEffect(player) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    player.play()
                }

                Lifecycle.Event.ON_PAUSE -> {
                    player.stop()
                }

                else -> {}
            }
        }

        val lifecycle = lifecycleOwner.value.lifecycle
        lifecycle.addObserver(observer)
        onDispose {
            player.release()
            lifecycle.removeObserver(observer)
        }
    }

}
