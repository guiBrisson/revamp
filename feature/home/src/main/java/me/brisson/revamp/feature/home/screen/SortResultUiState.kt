package me.brisson.revamp.feature.home.screen

import me.brisson.revam.core.model.WallpaperList

sealed interface SortResultUiState {
    data object Loading : SortResultUiState

    data class Success(
        val wallpaperList: WallpaperList
    ) : SortResultUiState
}
