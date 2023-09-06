package io.yoobi.poc.cleanarch.feature.dashboard.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.yoobi.poc.cleanarch.core.fragment.ui.IconToolbarBaseFragment
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.feature.dashboard.domain.exception.ExampleDashboardException
import io.yoobi.poc.cleanarch.feature.repository.ui.RepositoryAdapter
import io.yoobi.poc.cleanarch.features.dashboard.ui.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment: IconToolbarBaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val viewModel: DashboardViewModel by viewModels()
    @Inject lateinit var navigation: DashboardNavigation

    override fun toString(throwable: Throwable, context: Context): String {
        return if(throwable is ExampleDashboardException) "This is an example of error handling"
        else super.toString(throwable, context)
    }

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
                    Resource.Status.ERROR -> {
                        Snackbar.make(view, toString(it.error!!, view.context), Snackbar.LENGTH_SHORT).show()
                        Log.e("DashboardFragment", "${it.error}")
                    }
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
