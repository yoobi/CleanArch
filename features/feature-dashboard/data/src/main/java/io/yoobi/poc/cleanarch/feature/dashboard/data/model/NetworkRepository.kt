package io.yoobi.poc.cleanarch.feature.dashboard.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.Repository

@JsonClass(generateAdapter = true)
internal data class NetworkRepository(
    val name: String,
    val owner: NetworkUser,
    @Json(name="stargazers_count") val star: Int
)

internal fun NetworkRepository.toDomain() = Repository(name, owner.toDomain(), star)
