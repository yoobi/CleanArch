package io.yoobi.poc.cleanarch.feature.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import io.yoobi.poc.cleanarch.feature.repository.domain.model.use_cases.SearchRepositoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepositoryUseCase: SearchRepositoryUseCase
): ViewModel() {

    private val _repositories = MutableStateFlow<Resource<List<RepositoryDomainModel>>>(Resource.loading(null))
    val repositories = _repositories.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            runCatching {
                searchRepositoryUseCase.invoke(query)
            }.fold(
                onSuccess = { _repositories.value = Resource.success(it) },
                onFailure = { _repositories.value = Resource.error(it, null) }
            )
        }
    }
}
