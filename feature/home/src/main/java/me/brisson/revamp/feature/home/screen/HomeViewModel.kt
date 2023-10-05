package me.brisson.revamp.feature.home.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.revamp.core.domain.usecases.GetSortingWallpapersUseCase
import me.brisson.revamp.core.domain.usecases.WallpaperSorting
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sortingWallpapersUseCase: GetSortingWallpapersUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<SortResultUiState> =
        MutableStateFlow(SortResultUiState.Loading)
    val uiState: StateFlow<SortResultUiState> = _uiState.asStateFlow()

    fun setWallpaperSortingResult(sorting: WallpaperSorting) {
        viewModelScope.launch(Dispatchers.IO) {
            sortingWallpapersUseCase(sorting)
                .onStart {
                    _uiState.update { SortResultUiState.Loading }
                }
                .catch {
                    it.printStackTrace()
                }
                .collect { list ->
                    Log.d("HomeViewModel", "setWallpaperSortingResult: $list")
                    _uiState.update { SortResultUiState.Success(list) }
                }
        }
    }
}
