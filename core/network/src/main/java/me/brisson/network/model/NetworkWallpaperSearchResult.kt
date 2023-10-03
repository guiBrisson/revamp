package me.brisson.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkWallpaperSearchResult(
    val id: String,
    val url: String,
    @SerialName("short_url")
    val shortUrl: String,
    val views: Int,
    val favorites: Int,
    val source: String,
    val purity: String,
    val category: String,
    @SerialName("dimension_x")
    val dimensionX: Long,
    @SerialName("dimension_y")
    val dimensionY: Long,
    val resolution: String,
    val ratio: String,
    @SerialName("file_size")
    val fileSize: Long,
    @SerialName("file_type")
    val fileType: String,
    @SerialName("created_at")
    val createdAt: String,
    val colors: List<String>,
    @SerialName("path")
    val pathUrl: String,
    val thumbs: NetworkWallpaperThumbs,
)

@Serializable
data class NetworkWallpaperThumbs(
    @SerialName("large")
    val largeUrl: String,
    @SerialName("original")
    val originalUrl: String,
    @SerialName("small")
    val smallUrl: String,
)
