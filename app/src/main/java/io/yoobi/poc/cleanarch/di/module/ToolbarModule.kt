package io.yoobi.poc.cleanarch.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import io.yoobi.poc.cleanarch.core.fragment.ui.ToolbarManager
import io.yoobi.poc.cleanarch.di.impl.toolbar.ToolbarManagerImpl

@Module
@InstallIn(FragmentComponent::class)
interface ToolbarModule {

    @Binds
    fun providesToolbarManager(impl: ToolbarManagerImpl): ToolbarManager

}