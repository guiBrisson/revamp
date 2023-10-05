package me.brisson.network.retrofit

import me.brisson.network.model.NetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitWallHavenNetworkApi {
    @GET(value = "search")
    suspend fun getSearch(
        @Query("q") searchQuery: String?,
        @Query("page") page: Int?,
        @Query("sorting") sorting: String?, //date_added* , relevance, random, views, favorites, toplist
        @Query("order") order: String?, //desc*, asc
        @Query("colors") colors: List<String>?, //0066cc
        @Query("seed") seed: String? // [a-zA-Z0-9]{6}
    ): NetworkResponse<List<NetworkWallpaperSearchResult>>

    @GET(value = "w/{id}")
    suspend fun wallpaperDetail(
        @Path("id") wallpaperId: String,
    ): NetworkResponse<NetworkWallpaperDetail>

    @GET(value = "tag/{id}")
    suspend fun tagDetail(
        @Path("id") tagId: Long,
    ): NetworkResponse<NetworkWallpaperTag>
}
