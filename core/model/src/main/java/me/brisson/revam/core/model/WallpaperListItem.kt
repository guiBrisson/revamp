package me.brisson.revam.core.model

data class WallpaperListItem(
    val id: String,
    val category: String,
    val colors: List<String>,
    val thumbUrl: String,
)
