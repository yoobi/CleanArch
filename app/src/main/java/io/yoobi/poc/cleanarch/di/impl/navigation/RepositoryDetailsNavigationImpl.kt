package io.yoobi.poc.cleanarch.di.impl.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.scopes.FragmentScoped
import io.yoobi.poc.cleanarch.feature.repository.details.ui.RepositoryDetailsArgs
import io.yoobi.poc.cleanarch.feature.repository.details.ui.RepositoryDetailsFragmentArgs
import io.yoobi.poc.cleanarch.feature.repository.details.ui.RepositoryDetailsNavigation
import javax.inject.Inject

@FragmentScoped
class RepositoryDetailsNavigationImpl @Inject constructor(
    fragment: Fragment
): RepositoryDetailsNavigation {

    override val args: RepositoryDetailsArgs = run {
        val args = fragment.navArgs<RepositoryDetailsFragmentArgs>().value
        RepositoryDetailsArgs(args.authorName, args.repoName)
    }

}
