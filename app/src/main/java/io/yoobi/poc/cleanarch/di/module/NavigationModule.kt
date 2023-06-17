package io.yoobi.poc.cleanarch.di.module

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import io.yoobi.poc.cleanarch.R
import io.yoobi.poc.cleanarch.di.impl.navigation.DashboardNavigationImpl
import io.yoobi.poc.cleanarch.di.impl.navigation.RepositoryDetailsNavigationImpl
import io.yoobi.poc.cleanarch.di.impl.navigation.SearchNavigationImpl
import io.yoobi.poc.cleanarch.feature.dashboard.ui.DashboardNavigation
import io.yoobi.poc.cleanarch.feature.repository.details.ui.RepositoryDetailsNavigation
import io.yoobi.poc.cleanarch.feature.search.ui.SearchNavigation

@Module
@InstallIn(ActivityComponent::class)
object NavControllerModule {
    @Provides
    fun navController(activity: FragmentActivity): NavController {
        return NavHostFragment.findNavController(
            activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!
        )
    }
}

@Module
@InstallIn(FragmentComponent::class)
interface NavigationModule {
    @Binds
    fun bindsDashboardNavigation(impl: DashboardNavigationImpl): DashboardNavigation

    @Binds
    fun bindsSearchNavigation(impl: SearchNavigationImpl): SearchNavigation

    @Binds
    fun bindsRepositoryDetailsNavigation(impl: RepositoryDetailsNavigationImpl): RepositoryDetailsNavigation
}