package me.brisson.revamp.feature.home.screen

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
    private val _sortingUiState: MutableStateFlow<SortResultUiState> =
        MutableStateFlow(SortResultUiState.Loading)
    val sortingUiState: StateFlow<SortResultUiState> = _sortingUiState.asStateFlow()

    fun setWallpaperSortingResult(sorting: WallpaperSorting) {
        viewModelScope.launch(Dispatchers.IO) {
            sortingWallpapersUseCase(sorting)
                .onStart {
                    _sortingUiState.update { SortResultUiState.Loading }
                }
                .catch {
                    it.printStackTrace()
                }
                .collect { result ->
                    _sortingUiState.update { SortResultUiState.Success(result) }
                }
        }
    }

    fun loadMoreWallpapers(sorting: WallpaperSorting, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            sortingWallpapersUseCase(sorting, page)
                .catch { it.printStackTrace() }
                .collect { result ->
                    when (val state = sortingUiState.value) {
                        is SortResultUiState.Success -> {
                            val updatedList = state.wallpaperList.items.toMutableList().apply {
                                addAll(result.items)
                            }
                            val updatedResult = result.copy(items = updatedList)

                            _sortingUiState.update { SortResultUiState.Success(updatedResult) }
                        }

                        else -> {
                            _sortingUiState.update { SortResultUiState.Success(result) }
                        }
                    }
                }
        }
    }

}
