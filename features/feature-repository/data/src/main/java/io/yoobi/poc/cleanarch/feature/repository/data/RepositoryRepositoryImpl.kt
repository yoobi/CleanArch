package io.yoobi.poc.cleanarch.feature.repository.data

import io.yoobi.poc.cleanarch.feature.repository.data.model.toDomain
import io.yoobi.poc.cleanarch.feature.repository.domain.model.RepositoryRepository
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryRepositoryImpl @Inject constructor(
    retrofit: Retrofit
): RepositoryRepository {

    private val networkApi = retrofit.create(GithubRepositorySearchService::class.java)

    override suspend fun getTopAndroidRepository(): List<RepositoryDomainModel> =
        networkApi.getRepositoryBy("android+language%3Ajava+language%3Akotlin")
            .items.map { it.toDomain() }

    override suspend fun searchAndroidRepository(query: String): List<RepositoryDomainModel> =
        networkApi.getRepositoryBy("android+$query")
            .items.map { it.toDomain() }

    override suspend fun getRepository(author: String, name: String): RepositoryDomainModel =
        networkApi.getRepository(author, name).toDomain()

}
