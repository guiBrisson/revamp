package me.brisson.revamp.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.revamp.core.domain.usecases.WallpaperSorting
import me.brisson.revamp.core.module.designsystem.theme.RevampTheme
import me.brisson.revamp.feature.home.components.sortingButtons
import me.brisson.revamp.feature.home.components.wallpaperList

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val resultUiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        resultUiState = resultUiState,
        setSorting = { viewModel.setWallpaperSortingResult(it) },
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    resultUiState: SortResultUiState,
    setSorting: (WallpaperSorting) -> Unit,
) {
    var selectedSort by remember { mutableStateOf(WallpaperSorting.DATE_ADDED) }
    val itemSpan = 2

    LaunchedEffect(Unit) {
        setSorting(selectedSort)
    }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        columns = GridCells.Fixed(itemSpan),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 20.dp),
    ) {
        sortingButtons(
            itemSpan = itemSpan,
            selectedSort = selectedSort,
            onSortClick = { setSorting(it); selectedSort = it }
        )

        when (resultUiState) {
            SortResultUiState.Loading -> {
                item(span = { GridItemSpan(itemSpan) }) {
                    CircularProgressIndicator()
                }
            }

            is SortResultUiState.Success -> {
                wallpaperList(wallpaperList = resultUiState.wallpaperList.items)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    RevampTheme {
        val uiState = SortResultUiState.Loading
        var selectedSort by remember { mutableStateOf(WallpaperSorting.DATE_ADDED) }

        HomeScreen(resultUiState = uiState, setSorting = { selectedSort = it })
    }
}
