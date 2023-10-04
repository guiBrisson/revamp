package me.brisson.revamp.core.data.model

data class WallpaperList(
    val items: List<WallpaperListItem>,
    val currentPage: Int?,
    val lastPage: Int?,
    val perPage: Int?,
    val total: Int?,
    val query: String?,
)
