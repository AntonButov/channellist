package com.butovanton.channellist

import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem.fromUri
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.butovanton.channellist.presentation.theme.ChannelListTheme

class PlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra(CHANNEL_URL) ?: throw Exception("No url")
        setContent {
                ChannelListTheme {
                    Player(url)
                }
        }
    }

    companion object {
        const val CHANNEL_URL = "CHANNEL_URL"
    }
}

@OptIn(UnstableApi::class) @Composable
fun Player(url: String) {
    val hardCodedUrl = Uri.parse("https://albportal.net/albkanalemusic.m3u8") // todo remove when fix backend
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
