package me.brisson.revamp.feature.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.brisson.revamp.core.domain.usecases.WallpaperSorting


fun LazyGridScope.sortingButtons(
    itemSpan: Int,
    selectedSort: WallpaperSorting,
    onSortClick: (WallpaperSorting) -> Unit,
) {
    item(span = { GridItemSpan(itemSpan) }) {
        LazyRow(contentPadding = PaddingValues(horizontal = 12.dp)) {
            val sortList = enumValues<WallpaperSorting>()
            items(sortList) { sortingItem ->
                TextButton(onClick = { onSortClick(sortingItem) }) {
                    val textColor = if (selectedSort == sortingItem) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    }

                    Text(text = stringResource(id = sortingItem.resString), color = textColor)
                }
            }
        }
    }
}
