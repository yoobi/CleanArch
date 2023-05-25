package io.yoobi.poc.cleanarch.feature.dashboard.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.User

@JsonClass(generateAdapter = true)
internal data class NetworkUser(val login: String, @Json(name="avatar_url") val photo: String)

internal fun NetworkUser.toDomain() = User(login, photo)