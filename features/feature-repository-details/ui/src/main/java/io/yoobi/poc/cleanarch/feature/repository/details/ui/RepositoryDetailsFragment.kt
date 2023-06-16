package io.yoobi.poc.cleanarch.feature.repository.details.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.yoobi.poc.cleanarch.core.fragment.ui.BaseFragment
import io.yoobi.poc.cleanarch.core.fragment.ui.TextToolbarBaseFragment
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import io.yoobi.poc.cleanarch.features.repository.details.ui.R
import io.yoobi.poc.cleanarch.features.repository.details.ui.databinding.FragmentRepositoryDetailsBinding
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import io.yoobi.poc.cleanarch.app.res.R as appRes

import javax.inject.Inject

@AndroidEntryPoint
class RepositoryDetailsFragment: BaseFragment<FragmentRepositoryDetailsBinding>(
    FragmentRepositoryDetailsBinding::inflate
) {

    @Inject lateinit var repositoryDetailsArgs: RepositoryDetailsNavigation
    private val viewModel by viewModels<RepositoryDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load(repositoryDetailsArgs.args)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.repository.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                when(it.status) {
                    Resource.Status.SUCCESS -> bind(it.data!!)
                    Resource.Status.ERROR -> Snackbar.make(view, "${it.error}", Snackbar.LENGTH_SHORT).show()
                    Resource.Status.LOADING -> {}
                }
            }
        }
    }

    private fun bind(model: RepositoryDomainModel) {
        binding.tvRepositoryName.text = model.name
        binding.tvAuthorName.text = model.authorName
        binding.tvStars.text = getString(appRes.string.item_repository_stars, model.star)
        Glide.with(binding.imageUser).load(model.authorAvatar).into(binding.imageUser)
    }

}
