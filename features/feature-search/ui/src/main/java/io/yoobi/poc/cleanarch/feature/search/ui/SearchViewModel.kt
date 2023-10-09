package io.yoobi.poc.cleanarch.feature.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import io.yoobi.poc.cleanarch.feature.repository.domain.model.use_cases.GetSearchRepositoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchRepositoryUseCase: GetSearchRepositoryUseCase
): ViewModel() {

    private val _repositories = MutableStateFlow(Resource.loading<List<RepositoryDomainModel>>(null))
    val repositories = _repositories.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            _repositories.value = runCatching {
                getSearchRepositoryUseCase.invoke(query)
            }.fold(
                onSuccess = { Resource.success(it) },
                onFailure = { Resource.error(it, null) }
            )
        }
    }
}
