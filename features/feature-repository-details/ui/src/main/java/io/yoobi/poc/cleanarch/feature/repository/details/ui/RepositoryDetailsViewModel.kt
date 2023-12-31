package io.yoobi.poc.cleanarch.feature.repository.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import io.yoobi.poc.cleanarch.feature.repository.domain.model.use_cases.GetRepositoryByNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    private val getRepositoryByNameUseCase: GetRepositoryByNameUseCase
): ViewModel() {

    private val _repository = MutableStateFlow(Resource.loading<RepositoryDomainModel>(null))
    val repository = _repository.asStateFlow()

    fun load(args: RepositoryDetailsArgs) {
        viewModelScope.launch {
            _repository.value = runCatching {
                getRepositoryByNameUseCase.invoke(args.owner, args.repo)
            }.fold(
                onSuccess = { Resource.success(it) },
                onFailure = { Resource.error(it, null) }
            )
        }
    }

}
