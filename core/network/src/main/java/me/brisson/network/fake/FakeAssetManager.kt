package me.brisson.network.fake

import java.io.InputStream

fun interface FakeAssetManager {
    fun open(fileName: String): InputStream
}