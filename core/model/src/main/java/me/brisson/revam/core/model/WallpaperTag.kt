package me.brisson.revam.core.model

data class WallpaperTag(
    val id: Long,
    val name: String,
    val alias: String,
    val categoryId: Long,
    val category: String,
    val purity: String,
    val createdAt: String,
)
