package me.brisson.revamp.core.data.repository

import me.brisson.revamp.core.data.model.WallpaperList
import kotlinx.coroutines.flow.Flow
import me.brisson.revamp.core.data.model.Wallpaper
import me.brisson.revamp.core.data.model.WallpaperTag


interface WallpaperRepository {
    fun search(
        querySearch: String? = null,
        page: Int? = null,
        sorting: String? = null,
        order: String? = null,
        colors: List<String>? = null,
        seed: String? = null,
    ): Flow<WallpaperList>

    fun wallpaperDetail(id: String): Flow<Wallpaper>

    fun tagDetail(id: Long): Flow<WallpaperTag>
}