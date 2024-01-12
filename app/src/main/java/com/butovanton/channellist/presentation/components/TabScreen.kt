package com.butovanton.channellist.presentation.components

import android.widget.TableRow
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.butovanton.chanellist.R
import com.butovanton.channellist.presentation.theme.ChannelListTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

enum class TabScreen(@StringRes val nameId: Int) {
    All(nameId = R.string.all), Favorite(R.string.favorite)
}

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: TabScreen,
    onTabSelected: (TabScreen) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected.ordinal,
        modifier = modifier,
    ) {
        titles.forEachIndexed { index, text ->
            Text(
                modifier = Modifier.wrapContentSize(Alignment.Center).padding(bottom = 16.dp),
                text = text,
            )
        }
    }
}

@Preview
@Composable
fun TabsPreview() {
    ChannelListTheme {
        Tabs(
            titles = listOf("All", "Favorite"),
            tabSelected = TabScreen.Favorite,
            onTabSelected = {}
        )
    }
}

