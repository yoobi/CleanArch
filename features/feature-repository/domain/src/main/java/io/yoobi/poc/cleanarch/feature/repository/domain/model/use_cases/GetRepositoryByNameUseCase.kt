package io.yoobi.poc.cleanarch.feature.repository.domain.model.use_cases

import io.yoobi.poc.cleanarch.feature.repository.domain.model.RepositoryRepository
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import javax.inject.Inject

class GetRepositoryByNameUseCase @Inject constructor(
    private val repository: RepositoryRepository
) {

    suspend operator fun invoke(author: String, name: String): RepositoryDomainModel =
        repository.getRepository(author, name)

}
