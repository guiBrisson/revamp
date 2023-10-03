package me.brisson.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import me.brisson.network.WallpaperNetworkDataSource
import me.brisson.network.model.MetaNetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

private const val WALL_HAVEN_BASE_URL = "https://wallhaven.cc/api/v1/"

@Singleton
class RetrofitWallHavenNetwork @Inject constructor(
    networkJson: Json,
    okHttpCallFactory: Call.Factory,
) : WallpaperNetworkDataSource {

    private val networkApi: RetrofitWallHavenNetworkApi = Retrofit.Builder()
        .baseUrl(WALL_HAVEN_BASE_URL)
        .callFactory(okHttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitWallHavenNetworkApi::class.java)

    override suspend fun search(
        querySearch: String?,
        page: Int?,
        sorting: String?,
        order: String?,
        colors: List<String>?,
        seed: String?
    ): Pair<List<NetworkWallpaperSearchResult>, MetaNetworkResponse?> {
        val searchResult = networkApi.getSearch(querySearch, page, sorting, order, colors, seed)
        return Pair(searchResult.data, searchResult.meta)
    }

    override suspend fun wallpaperDetail(id: String): NetworkWallpaperDetail {
        return networkApi.wallpaperDetail(id).data
    }

    override suspend fun tagDetail(id: Long): NetworkWallpaperTag {
        return networkApi.tagDetail(id).data
    }

}
