package io.yoobi.poc.cleanarch.feature.repository.domain.model.use_cases

import io.yoobi.poc.cleanarch.feature.repository.domain.model.RepositoryRepository
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import javax.inject.Inject

class GetSearchRepositoryUseCase @Inject constructor(
    private val repository: RepositoryRepository
) {
    suspend operator fun invoke(query: String): List<RepositoryDomainModel> =
        repository.searchAndroidRepository(query)
}