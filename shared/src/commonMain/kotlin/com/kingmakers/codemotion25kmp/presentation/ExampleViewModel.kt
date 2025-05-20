package com.kingmakers.codemotion25kmp.presentation

import com.kingmakers.codemotion25kmp.domain.repositories.ExampleRepository
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.kingmakers.codemotion25kmp.domain.models.RandomUser
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import com.rickclephas.kmp.observableviewmodel.stateIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch


open class ExampleViewModel(
    private val exampleRepository: ExampleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExampleUiState>(ExampleUiState.Loading)
    val uiState = _uiState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExampleUiState.Loading)

    init {
        fetchRandomUser()
    }

    fun reloadRandomUser() {
        fetchRandomUser()
    }

    private fun fetchRandomUser() {
        _uiState.value = ExampleUiState.Loading
        viewModelScope.coroutineScope.launch {
            try {
                val result = exampleRepository.getRandomUser()
                result.fold(
                    onSuccess = { randomUser ->
                        _uiState.value = ExampleUiState.Success(randomUser)
                    },
                    onFailure = { exception ->
                        _uiState.value = ExampleUiState.Error(exception.message)
                    }
                )
            } catch (e: Exception) {
                _uiState.value = ExampleUiState.Error(e.message)
            }
        }
    }
}

sealed interface ExampleUiState {
    data class Success(val user: RandomUser) : ExampleUiState
    data class Error(val message: String?) : ExampleUiState
    data object Loading : ExampleUiState
}