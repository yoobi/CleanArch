package io.yoobi.poc.cleanarch.feature.dashboard.domain.use_cases

import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import io.yoobi.poc.cleanarch.feature.repository.domain.model.use_cases.TopAndroidRepositoryUseCase
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val topAndroidRepositoryUseCase: TopAndroidRepositoryUseCase
) {

    suspend operator fun invoke(): List<RepositoryDomainModel> = topAndroidRepositoryUseCase()

}
