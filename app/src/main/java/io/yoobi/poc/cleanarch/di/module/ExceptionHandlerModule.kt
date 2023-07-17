package io.yoobi.poc.cleanarch.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.yoobi.poc.cleanarch.core.fragment.ui.ExceptionHandler
import io.yoobi.poc.cleanarch.di.impl.ExceptionHandlerImpl

@Module
@InstallIn(SingletonComponent::class)
interface ExceptionHandlerModule {
    @Binds
    fun bindsExceptionHandler(impl: ExceptionHandlerImpl): ExceptionHandler
}
