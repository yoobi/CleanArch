package io.yoobi.poc.cleanarch.feature.dashboard.domain.use_cases

import io.yoobi.poc.cleanarch.feature.dashboard.domain.DashboardRepository
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.UserDomainModel
import java.util.Calendar
import javax.inject.Inject

class GetNewGithubUserUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {
    suspend operator fun invoke(date: Calendar): List<UserDomainModel> =
        dashboardRepository.getNewUserCreatedAt(date.toStringDate())

    private fun Calendar.toStringDate(): String {
        val year = get(Calendar.YEAR)
        val month = (get(Calendar.MONTH)+1).toString().padStart(2, '0')
        val day = get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
        return "$year-$month-$day"
    }

}
