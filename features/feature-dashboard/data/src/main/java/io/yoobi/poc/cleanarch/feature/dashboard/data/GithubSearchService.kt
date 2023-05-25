package io.yoobi.poc.cleanarch.feature.dashboard.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.yoobi.poc.cleanarch.feature.dashboard.data.model.NetworkRepository
import io.yoobi.poc.cleanarch.feature.dashboard.data.model.NetworkUser
import retrofit2.http.GET
import retrofit2.http.Query

@JsonClass(generateAdapter = true)
internal data class ApiResult<T>(
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "items") val items: List<T>)

internal interface GithubSearchService {

    @GET("search/users")
    suspend fun getUserBy(@Query("q") query: String): ApiResult<NetworkUser>

    @GET("search/repositories")
    suspend fun getRepositoryBy(
        @Query("q", encoded = true) query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): ApiResult<NetworkRepository>

}

