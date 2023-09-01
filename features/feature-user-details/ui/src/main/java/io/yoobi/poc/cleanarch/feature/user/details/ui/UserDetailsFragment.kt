package io.yoobi.poc.cleanarch.feature.user.details.ui

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import io.yoobi.poc.cleanarch.core.fragment.ui.TextToolbarBaseFragment
import io.yoobi.poc.cleanarch.features.repository.details.ui.databinding.FragmentUserDetailsBinding
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsFragment: TextToolbarBaseFragment<FragmentUserDetailsBinding>(
    FragmentUserDetailsBinding::inflate
) {

    @Inject lateinit var userDetailsArgs: UserDetailsNavigation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvUserName.text = userDetailsArgs.args.user
    }

}

