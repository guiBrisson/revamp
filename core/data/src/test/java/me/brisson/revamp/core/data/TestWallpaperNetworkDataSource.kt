package me.brisson.revamp.core.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.serialization.json.Json
import me.brisson.network.WallpaperNetworkDataSource
import me.brisson.network.fake.FakeWallpaperNetworkDataSource
import me.brisson.network.model.MetaNetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag

class TestWallpaperNetworkDataSource : WallpaperNetworkDataSource {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val source = FakeWallpaperNetworkDataSource(
        UnconfinedTestDispatcher(),
        Json { ignoreUnknownKeys = true }
    )

    override suspend fun search(
        querySearch: String?,
        page: Int?,
        sorting: String?,
        order: String?,
        colors: List<String>?,
        seed: String?
    ): Pair<List<NetworkWallpaperSearchResult>, MetaNetworkResponse?> =
        source.search(querySearch, page, sorting, order, colors, seed)

    override suspend fun wallpaperDetail(id: String): NetworkWallpaperDetail =
        source.wallpaperDetail(id)

    override suspend fun tagDetail(id: Long): NetworkWallpaperTag =
        source.tagDetail(id)
}
