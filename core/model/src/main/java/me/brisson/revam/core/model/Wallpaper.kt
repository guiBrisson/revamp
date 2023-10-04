package me.brisson.revam.core.model


data class Wallpaper(
    val id: String,
    val uploader: WallpaperUploader,
    val views: Int,
    val favorites: Int,
    val purity: String,
    val category: String,
    val resolution: String,
    val ratio: String,
    val fileSize: Long,
    val fileType: String,
    val createdAt: String,
    val colors: List<String>,
    val imageUrl: String,
    val tags: List<WallpaperTag>,
)
