package io.yoobi.poc.cleanarch.navigation

import android.util.Log
import androidx.navigation.NavController
import dagger.hilt.android.scopes.FragmentScoped
import io.yoobi.poc.cleanarch.feature.dashboard.ui.DashboardNavigation
import javax.inject.Inject

@FragmentScoped
class DashboardNavigationImpl @Inject constructor(
    private val navController: NavController
): DashboardNavigation {

    override fun navigateToDetailsRepository(owner: String, name: String) {
        Log.e("DashboardNav", "owner: $owner -- name: $name")
    }

    override fun navigateToDetailsUser(name: String) {
        Log.e("DashboardNav", "navController: $navController -- name: $name")
    }

}
