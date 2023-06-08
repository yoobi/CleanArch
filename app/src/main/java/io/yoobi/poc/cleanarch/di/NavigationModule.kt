package io.yoobi.poc.cleanarch.di

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
import io.yoobi.poc.cleanarch.feature.dashboard.ui.DashboardNavigation
import io.yoobi.poc.cleanarch.navigation.DashboardNavigationImpl

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
interface NavigationFragModule {

    @Binds
    fun bindsDashboardNavigation(dashboardNavigationImpl: DashboardNavigationImpl): DashboardNavigation

}
