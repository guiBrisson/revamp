package me.brisson.revamp.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import me.brisson.revamp.feature.home.components.header
import me.brisson.revamp.feature.home.components.sortingButtons
import me.brisson.revamp.feature.home.components.wallpaperList

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val resultUiState by viewModel.sortingUiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        resultUiState = resultUiState,
        setSorting = viewModel::setWallpaperSortingResult,
        loadMoreWallpapers = viewModel::loadMoreWallpapers
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    resultUiState: SortResultUiState,
    setSorting: (WallpaperSorting) -> Unit,
    loadMoreWallpapers: (WallpaperSorting, page: Int) -> Unit,
) {
    val scrollState = rememberLazyGridState()
    var selectedSort by remember { mutableStateOf(WallpaperSorting.TOP_LIST) }
    val itemSpan = 2

    LoadMoreWallpaperOnGridState(scrollState, resultUiState) { nextPage ->
        loadMoreWallpapers(selectedSort, nextPage)
    }

    LaunchedEffect(Unit) {
        setSorting(selectedSort)
    }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        state = scrollState,
        columns = GridCells.Fixed(itemSpan),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 20.dp),
    ) {
        header(
            modifier = Modifier.padding(top = 32.dp),
            itemSpan = itemSpan,
        )

        sortingButtons(
            itemSpan = itemSpan,
            selectedSort = selectedSort,
            onSortClick = { setSorting(it); selectedSort = it }
        )

        when (resultUiState) {
            SortResultUiState.Loading -> {
                item(span = { GridItemSpan(itemSpan) }) {
                    LinearProgressIndicator()
                }
            }

            is SortResultUiState.Success -> {
                wallpaperList(wallpaperList = resultUiState.wallpaperList.items)
            }
        }

    }
}

@Composable
private fun LoadMoreWallpaperOnGridState(
    scrollState: LazyGridState,
    resultUiState: SortResultUiState,
    onLoadNextPage: (page: Int) -> Unit
) {
    val endOfListReached by remember {
        derivedStateOf { scrollState.isScrolledToEnd() }
    }

    LaunchedEffect(endOfListReached) {
        when (resultUiState) {
            is SortResultUiState.Success -> {
                val currentPage = resultUiState.wallpaperList.currentPage ?: 1
                val lastPage = resultUiState.wallpaperList.lastPage ?: 1

                if (currentPage < lastPage) {
                    onLoadNextPage(currentPage + 1)
                }
            }

            else -> Unit
        }
    }
}

fun LazyGridState.isScrolledToEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Preview
@Composable
private fun PreviewHomeScreen() {
    RevampTheme {
        val uiState = SortResultUiState.Loading
        var selectedSort by remember { mutableStateOf(WallpaperSorting.DATE_ADDED) }

        HomeScreen(
            resultUiState = uiState,
            setSorting = { selectedSort = it },
            loadMoreWallpapers = { _, _ -> },
        )
    }
}
