package me.brisson.revamp.core.domain.usecases

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.Flow
import me.brisson.revam.core.model.WallpaperList
import me.brisson.revamp.core.data.repository.WallpaperRepository
import me.brisson.revamp.core.domain.R
import javax.inject.Inject

class GetSortingWallpapersUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    operator fun invoke(sorting: WallpaperSorting, page: Int = 1): Flow<WallpaperList> {
        return wallpaperRepository.search(sorting = sorting.apiString, page = page)
    }
}

enum class WallpaperSorting(val apiString: String, @StringRes val resString: Int,) {
    DATE_ADDED(apiString = "date_added", resString = R.string.date_added_sorting),
    TOP_LIST(apiString = "toplist", resString = R.string.top_list_sorting),
    RANDOM(apiString = "random", resString = R.string.random_sorting),
    RELEVANCE(apiString = "relevance", resString = R.string.relevance_sorting),
    VIEWS(apiString = "views", resString = R.string.views_sorting),
    FAVORITES(apiString = "favorites", resString = R.string.favorites_sorting),
}
