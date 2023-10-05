package me.brisson.revamp.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.brisson.revam.core.model.WallpaperListItem

fun LazyGridScope.wallpaperList(
    wallpaperList: List<WallpaperListItem>,
) {
    itemsIndexed(wallpaperList) { index, wallpaper ->
        val padding = if (index % 2 == 0) {
            PaddingValues(start = 20.dp)
        } else {
            PaddingValues(end = 20.dp)
        }

        Box(
            modifier = Modifier
                .padding(padding)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .aspectRatio(0.8f)
                .background(Color.Gray),
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = wallpaper.thumbUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}