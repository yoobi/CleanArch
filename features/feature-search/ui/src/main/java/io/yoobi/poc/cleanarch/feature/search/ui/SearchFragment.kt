package io.yoobi.poc.cleanarch.feature.search.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.yoobi.poc.cleanarch.core.fragment.ui.TextToolbarBaseFragment
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.feature.repository.ui.RepositoryAdapter
import io.yoobi.poc.cleanarch.features.search.ui.R
import io.yoobi.poc.cleanarch.features.search.ui.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment: TextToolbarBaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    @Inject lateinit var searchNavigation: SearchNavigation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val repositoryAdapter = RepositoryAdapter(RepositoryAdapter.RepositoryListener { owner, name ->
            searchNavigation.navigateToDetails(owner, name)
        })
        binding.rv.adapter = repositoryAdapter
        binding.searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query ?: return true
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // For Simplicity do nothing
                return false
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.repositories.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                when(it.status) {
                    Resource.Status.SUCCESS -> repositoryAdapter.submitList(it.data)
                    Resource.Status.ERROR -> Snackbar.make(view, "${it.error}", Snackbar.LENGTH_SHORT).show()
                    Resource.Status.LOADING -> {}
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("SearchFragment", "selected: ${item.title}")
        return super.onOptionsItemSelected(item)
    }

}
