package io.yoobi.poc.cleanarch.feature.repository.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel

@JsonClass(generateAdapter = true)
internal data class UserNetworkModel(
    val login: String,
    @Json(name = "avatar_url") val photo: String
)

@JsonClass(generateAdapter = true)
internal data class RepositoryNetworkModel(
    val name: String,
    val owner: UserNetworkModel,
    @Json(name="stargazers_count") val star: Int
)

internal fun RepositoryNetworkModel.toDomain() =
    RepositoryDomainModel(name, owner.login, owner.photo, star)
