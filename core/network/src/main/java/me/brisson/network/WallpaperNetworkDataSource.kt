package me.brisson.network

import me.brisson.network.model.MetaNetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag

interface WallpaperNetworkDataSource {
    suspend fun search(
        querySearch: String,
        page: Int?,
        sorting: String?,
        order: String?,
        colors: List<String>?,
        seed: String?,
    ): Pair<List<NetworkWallpaperSearchResult>, MetaNetworkResponse?>

    suspend fun wallpaperDetail(id: String): NetworkWallpaperDetail

    suspend fun tagDetail(id: String): NetworkWallpaperTag
}
