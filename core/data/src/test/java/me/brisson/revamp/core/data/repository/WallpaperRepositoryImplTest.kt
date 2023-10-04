package me.brisson.revamp.core.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import me.brisson.revamp.core.data.TestWallpaperNetworkDataSource
import me.brisson.revamp.core.data.model.asEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WallpaperRepositoryImplTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: WallpaperRepositoryImpl

    private lateinit var network: TestWallpaperNetworkDataSource

    @Before
    fun setup() {
        network = TestWallpaperNetworkDataSource()
        subject = WallpaperRepositoryImpl(network)
    }

    @Test
    fun `WallpaperRepositoryImpl search is backed by network`() = testScope.runTest {
        assertEquals(
            network.search().asEntity(),
            subject.search().first()
        )
    }

    @Test
    fun `WallpaperRepositoryImpl wallpaper detail is backed by network`() = testScope.runTest {
        assertEquals(
            network.wallpaperDetail("").asEntity(),
            subject.wallpaperDetail("").first()
        )
    }

    @Test
    fun `WallpaperRepositoryImpl tag detail is backed by network`() = testScope.runTest {
        assertEquals(
            network.tagDetail(1).asEntity(),
            subject.tagDetail(1).first()
        )
    }
}