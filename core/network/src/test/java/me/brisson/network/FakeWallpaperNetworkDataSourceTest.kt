package me.brisson.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import me.brisson.network.fake.FakeWallpaperNetworkDataSource
import JvmUnitTestFakeAssetManager
import me.brisson.network.model.MetaNetworkResponse
import me.brisson.network.model.NetworkWallpaperDetail
import me.brisson.network.model.NetworkWallpaperSearchResult
import me.brisson.network.model.NetworkWallpaperTag
import me.brisson.network.model.NetworkWallpaperThumbs
import me.brisson.network.model.NetworkWallpaperUploader
import me.brisson.network.model.NetworkWallpaperUploaderAvatar
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class FakeWallpaperNetworkDataSourceTest {

    private lateinit var subject: FakeWallpaperNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        subject = FakeWallpaperNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFakeAssetManager,
        )
    }

    @Test
    fun `Test deserialization of search result`() = runTest(testDispatcher) {
        val expectedFirstDataObject = NetworkWallpaperSearchResult(
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

        // Asserting the first data object
        assertEquals(
            expectedFirstDataObject,
            subject.search().first.first()
        )

        val expectedMetaObject = MetaNetworkResponse(
            currentPage = 1,
            lastPage = 1309,
            perPage = 24,
            total = 31398,
            query = "dark",
            seed = null,
        )

        // Asserting meta object
        assertEquals(
            expectedMetaObject,
            subject.search().second
        )
    }

    @Test
    fun `Test deserialization of wallpaper detail`() = runTest(testDispatcher) {
        val expectedWallpaper = NetworkWallpaperDetail(
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

        assertEquals(
            expectedWallpaper,
            subject.wallpaperDetail(expectedWallpaper.id)
        )
    }

    @Test
    fun `Test deserialization of tag detail`() = runTest(testDispatcher) {
        val expectedTag = NetworkWallpaperTag(
            id = 1,
            name = "anime",
            alias = "Chinese cartoons",
            categoryId = 1,
            category = "Anime & Manga",
            purity = "sfw",
            createdAt = "2015-01-16 02:06:45",
        )

        assertEquals(
            expectedTag,
            subject.tagDetail(expectedTag.id)
        )
    }

}
