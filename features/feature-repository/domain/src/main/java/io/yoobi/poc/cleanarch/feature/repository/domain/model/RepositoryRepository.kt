package io.yoobi.poc.cleanarch.feature.repository.domain.model

import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel

interface RepositoryRepository {

    suspend fun getTopAndroidRepository(): List<RepositoryDomainModel>

    suspend fun searchAndroidRepository(query: String): List<RepositoryDomainModel>

    suspend fun getRepository(author: String, name: String): RepositoryDomainModel

}
