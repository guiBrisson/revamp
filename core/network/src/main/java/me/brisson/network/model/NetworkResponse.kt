package me.brisson.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    val data: T,
    val meta: MetaNetworkResponse?,
)

@Serializable
data class MetaNetworkResponse(
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("last_page")
    val lastPage: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    val query: String?,
    val seed: String?,
)
