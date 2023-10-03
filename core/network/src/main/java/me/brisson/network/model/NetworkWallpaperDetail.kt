package me.brisson.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkWallpaperDetail(
    val id: String,
    val url: String,
    @SerialName("short_url")
    val shortUrl: String,
    val uploader: NetworkWallpaperUploader,
    val views: Int,
    val favourites: Int,
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
    val fileType: Long,
    @SerialName("created_at")
    val createdAt: String,
    val colors: List<String>,
    @SerialName("path")
    val pathUrl: String,
    val thumbs: NetworkWallpaperThumbs,
    val tags: List<NetworkWallpaperTag>,
)

@Serializable
data class NetworkWallpaperUploader(
    val username: String,
    val group: String,
    val avatar: NetworkWallpaperUploaderAvatar,
)

@Serializable
data class NetworkWallpaperUploaderAvatar(
    @SerialName("200px")
    val big: String,
    @SerialName("128px")
    val medium: String,
    @SerialName("32px")
    val small: String,
    @SerialName("20px")
    val smaller: String,
)

@Serializable
data class NetworkWallpaperTag(
    val id: Long,
    val name: String,
    val alias: String,
    @SerialName("category_id")
    val categoryId: String,
    val category: String,
    val purity: String,
    @SerialName("created_at")
    val createdAt: String,
)