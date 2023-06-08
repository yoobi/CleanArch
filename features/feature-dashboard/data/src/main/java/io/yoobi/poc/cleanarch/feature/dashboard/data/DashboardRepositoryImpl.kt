package io.yoobi.poc.cleanarch.feature.dashboard.data

import io.yoobi.poc.cleanarch.feature.dashboard.data.model.toDomain
import io.yoobi.poc.cleanarch.feature.dashboard.domain.DashboardRepository
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.Repository
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.User
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepositoryImpl @Inject constructor(
    retrofit: Retrofit
): DashboardRepository {

    private val networkApi = retrofit.create(GithubSearchService::class.java)

    override suspend fun getNewUserCreatedAt(date: String): List<User> =
        networkApi.getUserBy("created:$date").items.map { it.toDomain() }

    override suspend fun getTopAndroidRepository(): List<Repository> =
        networkApi.getRepositoryBy("android+language%3Ajava+language%3Akotlin")
            .items.map { it.toDomain() }

}
