package io.yoobi.poc.cleanarch.feature.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.UserDomainModel
import io.yoobi.poc.cleanarch.feature.dashboard.domain.use_cases.GetDashboardUseCase
import io.yoobi.poc.cleanarch.feature.dashboard.domain.use_cases.GetNewGithubUserUseCase
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardUseCase: GetDashboardUseCase,
    private val getNewGithubUserUseCase: GetNewGithubUserUseCase
): ViewModel() {

    private val _newestUser = MutableStateFlow<Resource<List<UserDomainModel>>>(Resource.loading(null))
    val newestUser = _newestUser.asStateFlow()

    private val _topRepository = MutableStateFlow<Resource<List<RepositoryDomainModel>>>(Resource.loading(null))
    val topRepository = _topRepository.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                getNewGithubUserUseCase(Calendar.getInstance())
            }.fold(
                onSuccess = {
                    _newestUser.value = Resource.success(it)
                },
                onFailure = {
                    _newestUser.value = Resource.error(it, null)
                }
            )
        }
        viewModelScope.launch {
            runCatching {
                getDashboardUseCase()
            }.fold(
                onSuccess = {
                    _topRepository.value = Resource.success(it)
                },
                onFailure = {
                    _topRepository.value = Resource.error(it, null)
                }
            )
        }
    }

}
