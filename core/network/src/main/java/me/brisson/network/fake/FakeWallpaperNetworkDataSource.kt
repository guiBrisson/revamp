package me.brisson.network.fake

import JvmUnitTestFakeAssetManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import me.brisson.network.WallpaperNetworkDataSource
import me.brisson.network.model.MetaNetworkResponse
import me.brisson.network.model.NetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag
import javax.inject.Inject

class FakeWallpaperNetworkDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : WallpaperNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun search(
        querySearch: String?,
        page: Int?,
        sorting: String?,
        order: String?,
        colors: List<String>?,
        seed: String?
    ): Pair<List<NetworkWallpaperSearchResult>, MetaNetworkResponse?> {
        return withContext(ioDispatcher) {
            val result: NetworkResponse<List<NetworkWallpaperSearchResult>> =
                assets.open(SEARCH_RESULT_ASSET).use(networkJson::decodeFromStream)

            Pair(result.data, result.meta)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun wallpaperDetail(id: String): NetworkWallpaperDetail {
         return withContext(ioDispatcher) {
             val result: NetworkResponse<NetworkWallpaperDetail> =
                 assets.open(WALLPAPER_ASSET).use(networkJson::decodeFromStream)

             result.data
         }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun tagDetail(id: Long): NetworkWallpaperTag {
        return withContext(ioDispatcher) {
            val result: NetworkResponse<NetworkWallpaperTag> =
                assets.open(TAG_ASSET).use(networkJson::decodeFromStream)

            result.data
        }
    }

    companion object {
        private const val SEARCH_RESULT_ASSET = "search_result.json"
        private const val WALLPAPER_ASSET = "wallpaper.json"
        private const val TAG_ASSET = "tag.json"
    }
}
