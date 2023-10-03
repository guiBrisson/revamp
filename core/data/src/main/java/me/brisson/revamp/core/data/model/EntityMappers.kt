package me.brisson.revamp.core.data.model

import me.brisson.network.model.MetaNetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag
import me.brisson.network.model.NetworkWallpaperUploader

fun Pair<List<NetworkWallpaperSearchResult>, MetaNetworkResponse?>.asEntity(): WallpaperList {
    return WallpaperList(
        items = this.first.map { it.asEntity() },
        currentPage = this.second?.currentPage,
        lastPage = this.second?.lastPage,
        perPage = this.second?.perPage,
        total = this.second?.total,
        query = this.second?.query,
    )
}

fun NetworkWallpaperSearchResult.asEntity() = WallpaperListItem(
    id = this.id,
    category = this.category,
    colors = this.colors,
    thumbUrl = this.thumbs.originalUrl,
)

fun NetworkWallpaperDetail.asEntity() = Wallpaper(
    id = this.id,
    uploader = this.uploader.asEntity(),
    views = this.views,
    favorites = this.favorites,
    purity = this.purity,
    category = this.category,
    resolution = this.resolution,
    ratio = this.ratio,
    fileSize = this.fileSize,
    fileType = this.fileType,
    createdAt = this.createdAt,
    colors = this.colors,
    imageUrl = this.pathUrl,
    tags = this.tags.map { it.asEntity() }
)

fun NetworkWallpaperUploader.asEntity() = WallpaperUploader(
    username = this.username,
    group = this.group,
    avatarUrl = this.avatar.medium,
)

fun NetworkWallpaperTag.asEntity() = WallpaperTag(
    id = this.id,
    name = this.name,
    alias = this.alias,
    categoryId = this.categoryId,
    category = this.category,
    purity = this.purity,
    createdAt = this.createdAt,
)