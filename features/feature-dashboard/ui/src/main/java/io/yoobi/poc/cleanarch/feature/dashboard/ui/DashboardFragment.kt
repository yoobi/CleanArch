package io.yoobi.poc.cleanarch.feature.dashboard.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.yoobi.poc.cleanarch.core.fragment.ui.BaseFragment
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.features.dashboard.ui.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment: BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private val viewModel: DashboardViewModel by viewModels()
    @Inject lateinit var navigation: DashboardNavigation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAdapter = UserAdapter(UserAdapter.UserClickListener { name ->
            navigation.navigateToDetailsUser(name)
        })
        val repositoryAdapter = RepositoryAdapter(RepositoryAdapter.RepositoryListener { owner, name ->
            navigation.navigateToDetailsRepository(owner, name)
        })
        binding.rvUser.adapter = userAdapter
        binding.rvRepo.adapter = repositoryAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.newestUser.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                when(it.status) {
                    Resource.Status.SUCCESS -> userAdapter.submitList(it.data)
                    Resource.Status.ERROR -> Snackbar.make(view, "${it.error}", Snackbar.LENGTH_SHORT).show()
                    Resource.Status.LOADING -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.topRepository.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                when(it.status) {
                    Resource.Status.SUCCESS -> repositoryAdapter.submitList(it.data)
                    Resource.Status.ERROR -> Snackbar.make(view, "${it.error}", Snackbar.LENGTH_SHORT).show()
                    Resource.Status.LOADING -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        binding.rvRepo.adapter = null
        binding.rvUser.adapter = null
        super.onDestroyView()
    }
}
