package me.brisson.network

import me.brisson.network.model.MetaNetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag

interface WallpaperNetworkDataSource {
    suspend fun search(
        querySearch: String? = null,
        page: Int? = null,
        sorting: String? = null,
        order: String? = null,
        colors: List<String>? = null,
        seed: String? = null,
    ): Pair<List<NetworkWallpaperSearchResult>, MetaNetworkResponse?>

    suspend fun wallpaperDetail(id: String): NetworkWallpaperDetail

    suspend fun tagDetail(id: Long): NetworkWallpaperTag
}
