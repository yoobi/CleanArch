package io.yoobi.poc.cleanarch.feature.dashboard.domain

import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.UserDomainModel

interface DashboardRepository {

    suspend fun getNewUserCreatedAt(date: String): List<UserDomainModel>

}
