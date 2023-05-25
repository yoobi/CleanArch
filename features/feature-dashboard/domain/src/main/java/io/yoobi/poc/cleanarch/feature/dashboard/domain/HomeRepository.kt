package io.yoobi.poc.cleanarch.feature.dashboard.domain

import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.Repository
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.User


interface HomeRepository {

    suspend fun getNewUserCreatedAt(date: String): List<User>

    suspend fun getTopAndroidRepository(): List<Repository>

}
