package io.yoobi.poc.cleanarch.feature.dashboard.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.UserDomainModel

@JsonClass(generateAdapter = true)
internal data class UserRemoteModel(val login: String, @Json(name="avatar_url") val photo: String)

internal fun UserRemoteModel.toDomain() = UserDomainModel(login, photo)