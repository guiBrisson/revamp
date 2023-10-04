package me.brisson.revamp.core.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.brisson.revam.core.model.WallpaperList
import me.brisson.revamp.core.data.repository.WallpaperRepository
import javax.inject.Inject

class GetSortingWallpapersUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    operator fun invoke(sorting: WallpaperSorting, page: Int = 1): Flow<WallpaperList> {
        return wallpaperRepository.search(sorting = sorting.string, page = page)
    }
}

/**
 * @param string value that matches the API's
 */
enum class WallpaperSorting(val string: String) {
    DATE_ADDED(string = "date_added"),
    RELEVANCE(string = "relevance"),
    RANDOM(string = "random"),
    VIEWS(string = "views"),
    FAVORITES(string = "favorites"),
    TOP_LIST(string = "toplist"),
}
