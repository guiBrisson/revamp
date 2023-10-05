package me.brisson.revamp.core.data.model

import me.brisson.network.model.MetaNetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag
import me.brisson.network.model.NetworkWallpaperThumbs
import me.brisson.network.model.NetworkWallpaperUploader
import me.brisson.network.model.NetworkWallpaperUploaderAvatar
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkEntityKtTest {

    @Test
    fun `NetworkWallpaperTag can be mapped to WallpaperTag entity`() {
        val networkModel = NetworkWallpaperTag(
            id = 1,
            name = "anime",
            alias = "Chinese cartoons",
            categoryId = 1,
            category = "Anime & Manga",
            purity = "sfw",
            createdAt = "2015-01-16 02:06:45",
        )
        val entity = networkModel.asEntity()

        assertEquals(1, entity.id)
        assertEquals("anime", entity.name)
        assertEquals("Chinese cartoons", entity.alias)
        assertEquals(1, entity.categoryId)
        assertEquals("Anime & Manga", entity.category)
        assertEquals("sfw", entity.purity)
        assertEquals("2015-01-16 02:06:45", entity.createdAt)
    }

    @Test
    fun `NetworkWallpaperUploader can be mapped to WallpaperUploader entity`() {
        val networkModel = NetworkWallpaperUploader(
            username = "gkarsten6",
            group = "User",
            avatar = NetworkWallpaperUploaderAvatar(
                big = "https://wallhaven.cc/images/user/avatar/200/default-avatar.jpg",
                medium = "https://wallhaven.cc/images/user/avatar/128/default-avatar.jpg",
                small = "https://wallhaven.cc/images/user/avatar/32/default-avatar.jpg",
                smaller = "https://wallhaven.cc/images/user/avatar/20/default-avatar.jpg"
            )
        )
        val entity = networkModel.asEntity()

        assertEquals("gkarsten6", entity.username)
        assertEquals("User", entity.group)
        assertEquals(
            "https://wallhaven.cc/images/user/avatar/128/default-avatar.jpg",
            entity.avatarUrl
        )
    }

    @Test
    fun `NetworkWallpaperSearchResult can be mapped to WallpaperListItem entity`() {
        val networkModel = NetworkWallpaperSearchResult(
            id = "1po8r1",
            url = "https://wallhaven.cc/w/1po8r1",
            shortUrl = "https://whvn.cc/1po8r1",
            views = 110,
            favorites = 1,
            source = "",
            purity = "sfw",
            category = "general",
            dimensionX = 1920,
            dimensionY = 1080,
            resolution = "1920x1080",
            ratio = "1.78",
            fileSize = 4682736,
            fileType = "image/png",
            createdAt = "2023-10-03 14:56:34",
            colors = listOf(
                "#000000",
                "#663300",
                "#424153",
                "#660000",
                "#996633"
            ),
            pathUrl = "https://w.wallhaven.cc/full/1p/wallhaven-1po8r1.png",
            thumbs = NetworkWallpaperThumbs(
                largeUrl = "https://th.wallhaven.cc/lg/1p/1po8r1.jpg",
                originalUrl = "https://th.wallhaven.cc/orig/1p/1po8r1.jpg",
                smallUrl = "https://th.wallhaven.cc/small/1p/1po8r1.jpg"
            )
        )
        val entity = networkModel.asEntity()

        assertEquals("1po8r1", entity.id)
        assertEquals("general", entity.category)
        assertEquals(listOf("#000000", "#663300", "#424153", "#660000", "#996633"), entity.colors)
        assertEquals("https://th.wallhaven.cc/lg/1p/1po8r1.jpg", entity.thumbUrl)
    }

    @Test
    fun `Network search response can be mapped to WallpaperList entity`() {
        val dataNetwork = NetworkWallpaperSearchResult(
            id = "1po8r1",
            url = "https://wallhaven.cc/w/1po8r1",
            shortUrl = "https://whvn.cc/1po8r1",
            views = 110,
            favorites = 1,
            source = "",
            purity = "sfw",
            category = "general",
            dimensionX = 1920,
            dimensionY = 1080,
            resolution = "1920x1080",
            ratio = "1.78",
            fileSize = 4682736,
            fileType = "image/png",
            createdAt = "2023-10-03 14:56:34",
            colors = listOf(
                "#000000",
                "#663300",
                "#424153",
                "#660000",
                "#996633"
            ),
            pathUrl = "https://w.wallhaven.cc/full/1p/wallhaven-1po8r1.png",
            thumbs = NetworkWallpaperThumbs(
                largeUrl = "https://th.wallhaven.cc/lg/1p/1po8r1.jpg",
                originalUrl = "https://th.wallhaven.cc/orig/1p/1po8r1.jpg",
                smallUrl = "https://th.wallhaven.cc/small/1p/1po8r1.jpg"
            )
        )
        val metaNetwork = MetaNetworkResponse(
            currentPage = 1,
            lastPage = 1309,
            perPage = 24,
            total = 31398,
            query = "dark",
            seed = null,
        )

        val networkSearchResponse = Pair(listOf(dataNetwork), metaNetwork)

        val entity = networkSearchResponse.asEntity()

        assertEquals(listOf(dataNetwork).map { it.asEntity() }, entity.items)
        assertEquals(1, entity.currentPage)
        assertEquals(1309, entity.lastPage)
        assertEquals(24, entity.perPage)
        assertEquals(31398, entity.total)
        assertEquals("dark", entity.query)
    }

    @Test
    fun `NetworkWallpaperDetail can be mapped to Wallpaper entity`() {
        val networkModel = NetworkWallpaperDetail(
            id = "85j2g2",
            url = "https://wallhaven.cc/w/85j2g2",
            shortUrl = "https://whvn.cc/85j2g2",
            uploader = NetworkWallpaperUploader(
                username = "gkarsten6",
                group = "User",
                avatar = NetworkWallpaperUploaderAvatar(
                    big = "https://wallhaven.cc/images/user/avatar/200/default-avatar.jpg",
                    medium = "https://wallhaven.cc/images/user/avatar/128/default-avatar.jpg",
                    small = "https://wallhaven.cc/images/user/avatar/32/default-avatar.jpg",
                    smaller = "https://wallhaven.cc/images/user/avatar/20/default-avatar.jpg"
                )
            ),
            views = 274,
            favorites = 1,
            source = "",
            purity = "sfw",
            category = "anime",
            dimensionX = 1920,
            dimensionY = 1073,
            resolution = "1920x1073",
            ratio = "1.79",
            fileSize = 1192523,
            fileType = "image/png",
            createdAt = "2023-10-01 17:23:13",
            colors = listOf(
                "#000000",
                "#424153",
                "#999999",
                "#996633",
                "#660000",
            ),
            pathUrl = "https://w.wallhaven.cc/full/85/wallhaven-85j2g2.png",
            thumbs = NetworkWallpaperThumbs(
                largeUrl = "https://th.wallhaven.cc/lg/85/85j2g2.jpg",
                originalUrl = "https://th.wallhaven.cc/orig/85/85j2g2.jpg",
                smallUrl = "https://th.wallhaven.cc/small/85/85j2g2.jpg"
            ),
            tags = listOf(
                NetworkWallpaperTag(
                    id = 108966,
                    name = "Jujutsu Kaisen",
                    alias = "Jujutsukaisen",
                    categoryId = 22,
                    category = "Series",
                    purity = "sfw",
                    createdAt = "2020-11-13 22:45:04"
                )
            ),
        )

        val entity = networkModel.asEntity()

        assertEquals("85j2g2", entity.id)
        assertEquals("gkarsten6", entity.uploader.username)
        assertEquals("User", entity.uploader.group)
        assertEquals(
            "https://wallhaven.cc/images/user/avatar/128/default-avatar.jpg",
            entity.uploader.avatarUrl
        )
        assertEquals(274, entity.views)
        assertEquals(1, entity.favorites)
        assertEquals("sfw", entity.purity)
        assertEquals("anime", entity.category)
        assertEquals("1920x1073", entity.resolution)
        assertEquals("1.79", entity.ratio)
        assertEquals(1192523, entity.fileSize)
        assertEquals("image/png", entity.fileType)
        assertEquals("2023-10-01 17:23:13", entity.createdAt)
        assertEquals(listOf("#000000", "#424153", "#999999", "#996633", "#660000"), entity.colors)
        assertEquals("https://w.wallhaven.cc/full/85/wallhaven-85j2g2.png", entity.imageUrl)
        assertEquals(networkModel.tags.map { it.asEntity() }, entity.tags)
    }
}