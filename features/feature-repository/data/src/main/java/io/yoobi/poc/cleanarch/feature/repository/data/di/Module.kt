package io.yoobi.poc.cleanarch.feature.repository.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.yoobi.poc.cleanarch.feature.repository.data.RepositoryRepositoryImpl
import io.yoobi.poc.cleanarch.feature.repository.domain.model.RepositoryRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    @Binds
    abstract fun bindsRepositoryRepository(impl: RepositoryRepositoryImpl): RepositoryRepository

}
