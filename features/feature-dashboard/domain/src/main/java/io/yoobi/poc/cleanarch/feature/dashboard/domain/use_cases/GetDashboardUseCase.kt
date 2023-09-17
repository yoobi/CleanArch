package io.yoobi.poc.cleanarch.feature.dashboard.domain.use_cases

import io.yoobi.poc.cleanarch.feature.dashboard.domain.exception.ExampleDashboardException
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import io.yoobi.poc.cleanarch.feature.repository.domain.model.use_cases.GetTopAndroidRepositoryUseCase
import javax.inject.Inject

class GetDashboardUseCase @Inject constructor(
    private val getTopAndroidRepositoryUseCase: GetTopAndroidRepositoryUseCase
) {

    suspend operator fun invoke(): List<RepositoryDomainModel> {
        throw ExampleDashboardException()
        return getTopAndroidRepositoryUseCase()
    }

}
