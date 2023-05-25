package io.yoobi.poc.cleanarch.feature.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.yoobi.poc.cleanarch.core.network.Resource
import io.yoobi.poc.cleanarch.feature.dashboard.domain.HomeRepository
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.Repository
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    private val _newestUser = MutableStateFlow<Resource<List<User>>>(Resource.loading(null))
    val newestUser = _newestUser.asStateFlow()

    private val _topRepository = MutableStateFlow<Resource<List<Repository>>>(Resource.loading(null))
    val topRepository = _topRepository.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                val calendar = Calendar.getInstance()
                homeRepository.getNewUserCreatedAt(calendar.toStringDate())
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
                homeRepository.getTopAndroidRepository()
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

    private fun Calendar.toStringDate(): String {
        val year = get(Calendar.YEAR)
        val month = (get(Calendar.MONTH)+1).toString().padStart(2, '0')
        val day = get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
        return "$year-$month-$day"
    }

}