package com.butovanton.channellist

import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem.fromUri
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class PlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyPlayer()
                }
        }
    }
}

@Composable
fun MyPlayer() {
  //  val hlsUri = Uri.parse("https:\\/\\/mhd.iptv2022.com\\/p\\/uIADBnajR1ufdeqQR98g6g,1703196009\\/streaming\\/muztv\\/324\\/1\\/index.m3u8")
  //  val hlsUri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
    val hlsUri = Uri.parse("https://albportal.net/albkanalemusic.m3u8")
    val context = LocalContext.current
    val player: ExoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                setMediaItem(fromUri(hlsUri))
                playWhenReady = true
                prepare()
            }
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
                this.player = player
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        MyPlayer()
    }
}