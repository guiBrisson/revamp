package me.brisson.revamp.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.brisson.network.WallpaperNetworkDataSource
import me.brisson.revam.core.model.Wallpaper
import me.brisson.revam.core.model.WallpaperList
import me.brisson.revam.core.model.WallpaperTag
import me.brisson.revamp.core.data.model.asEntity
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private val network: WallpaperNetworkDataSource
) : WallpaperRepository {

    override fun search(
        querySearch: String?,
        page: Int?,
        sorting: String?,
        order: String?,
        colors: List<String>?,
        seed: String?
    ): Flow<WallpaperList> = flow {
        val wallpaperSearchResult = network.search(querySearch, page, sorting, order, colors, seed)
        emit(wallpaperSearchResult.asEntity())
    }

    override fun wallpaperDetail(id: String): Flow<Wallpaper> = flow {
        val wallpaperDetailResult = network.wallpaperDetail(id)
        emit(wallpaperDetailResult.asEntity())
    }

    override fun tagDetail(id: Long): Flow<WallpaperTag> = flow {
        val tagDetailResult = network.tagDetail(id)
        emit(tagDetailResult.asEntity())
    }

}
