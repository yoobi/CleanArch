package io.yoobi.poc.cleanarch.di.impl.navigation

import androidx.navigation.NavController
import dagger.hilt.android.scopes.FragmentScoped
import io.yoobi.poc.cleanarch.feature.search.ui.SearchFragmentDirections
import io.yoobi.poc.cleanarch.feature.search.ui.SearchNavigation
import javax.inject.Inject

@FragmentScoped
class SearchNavigationImpl @Inject constructor(
    private val navController: NavController
): SearchNavigation {

    override fun navigateToDetails(owner: String, repoName: String) {
        navController.navigate(
            SearchFragmentDirections.actionSearchFragmentToRepoDetailsFragment(repoName, owner)
        )
    }
}
