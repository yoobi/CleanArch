package io.yoobi.poc.cleanarch.feature.dashboard.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.yoobi.poc.cleanarch.feature.dashboard.data.model.UserRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

//TODO should be in network module?
@JsonClass(generateAdapter = true)
internal data class ApiResult<T>(
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "items") val items: List<T>
)

internal interface GithubUserSearchService {

    @GET("search/users")
    suspend fun getUserBy(@Query("q") query: String): ApiResult<UserRemoteModel>

}

