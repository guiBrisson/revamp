package me.brisson.revamp.feature.home.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.brisson.revamp.feature.home.R

fun LazyGridScope.header(
    modifier: Modifier = Modifier,
    itemSpan: Int,
    onToggleTheme: (Boolean) -> Unit,
) {
    item(span = { GridItemSpan(itemSpan) }) {
        Row(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val darkTheme = isSystemInDarkTheme()
            val iconSize = 24.dp

            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            IconButton(modifier = Modifier.size(iconSize), onClick = { onToggleTheme(!darkTheme) }) {
                val iconPainter: Painter = if (darkTheme) {
                    painterResource(id = R.drawable.ic_sun_fill)
                } else {
                    painterResource(id = R.drawable.ic_moon_fill)
                }

                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = iconPainter,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}
