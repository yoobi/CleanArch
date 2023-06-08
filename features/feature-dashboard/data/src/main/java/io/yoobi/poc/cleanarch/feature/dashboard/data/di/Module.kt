package io.yoobi.poc.cleanarch.feature.dashboard.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.yoobi.poc.cleanarch.feature.dashboard.data.DashboardRepositoryImpl
import io.yoobi.poc.cleanarch.feature.dashboard.domain.DashboardRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    @Binds
    abstract fun bindsDashboardRepository(impl: DashboardRepositoryImpl): DashboardRepository

}
