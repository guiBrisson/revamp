package me.brisson.revamp.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.brisson.revamp.core.data.repository.WallpaperRepository
import me.brisson.revamp.core.data.repository.WallpaperRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsWallpaperRepository(
        wallpaperRepository: WallpaperRepositoryImpl,
    ):WallpaperRepository
}