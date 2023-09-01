package io.yoobi.poc.cleanarch.di.impl.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.scopes.FragmentScoped
import io.yoobi.poc.cleanarch.feature.user.details.ui.UserDetailsArgs
import io.yoobi.poc.cleanarch.feature.user.details.ui.UserDetailsFragmentArgs
import io.yoobi.poc.cleanarch.feature.user.details.ui.UserDetailsNavigation
import javax.inject.Inject

@FragmentScoped
class UserDetailsNavigationImpl @Inject constructor(
    fragment: Fragment
): UserDetailsNavigation {

    override val args: UserDetailsArgs = run {
        val args = fragment.navArgs<UserDetailsFragmentArgs>().value
        UserDetailsArgs(args.userName)
    }

}
