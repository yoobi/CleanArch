package io.yoobi.poc.cleanarch.feature.repository.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.yoobi.poc.cleanarch.feature.repository.data.model.RepositoryRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@JsonClass(generateAdapter = true)
internal data class ApiListResult<T>(
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "items") val items: List<T>
)

internal interface GithubRepositorySearchService {

    @GET("search/repositories")
    suspend fun getRepositoryBy(
        @Query("q", encoded = true) query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): ApiListResult<RepositoryRemoteModel>

    @GET("repos/{author}/{name}")
    suspend fun getRepository(
        @Path("author") author: String,
        @Path("name") name: String
    ): RepositoryRemoteModel

}
