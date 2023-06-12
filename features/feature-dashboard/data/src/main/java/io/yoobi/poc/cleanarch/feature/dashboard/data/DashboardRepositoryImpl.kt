package io.yoobi.poc.cleanarch.feature.dashboard.data

import io.yoobi.poc.cleanarch.feature.dashboard.data.model.toDomain
import io.yoobi.poc.cleanarch.feature.dashboard.domain.DashboardRepository
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.UserDomainModel
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepositoryImpl @Inject constructor(
    retrofit: Retrofit
): DashboardRepository {

    private val networkApi = retrofit.create(GithubUserSearchService::class.java)

    override suspend fun getNewUserCreatedAt(date: String): List<UserDomainModel> =
        networkApi.getUserBy("created:$date").items.map { it.toDomain() }

}
