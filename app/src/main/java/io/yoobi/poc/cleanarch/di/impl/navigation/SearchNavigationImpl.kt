package io.yoobi.poc.cleanarch.di.impl.navigation

import android.util.Log
import androidx.navigation.NavController
import dagger.hilt.android.scopes.FragmentScoped
import io.yoobi.poc.cleanarch.feature.dashboard.ui.DashboardNavigation
import io.yoobi.poc.cleanarch.feature.search.ui.SearchNavigation
import javax.inject.Inject

@FragmentScoped
class SearchNavigationImpl @Inject constructor(
    private val navController: NavController
): SearchNavigation {

    override fun navigateToDetails(owner: String, repoName: String) {
        Log.e("SearchNavigationImpl", "owner: $owner -- repoName: $repoName")
    }
}
