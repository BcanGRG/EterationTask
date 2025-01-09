package com.bcan.eterationtask.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.data.model.ProductResponseModelDao
import com.bcan.eterationtask.data.repository.ProductsRepository
import com.bcan.eterationtask.data.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductsRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> =
        _uiState.onStart {
            getProducts()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3000),
            initialValue = _uiState.value
        )

    private fun getProducts() {
        viewModelScope.launch {
            repository.getProducts().collect { result ->
                when (result) {
                    is NetworkResult.OnLoading -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = true,
                                errorMessage = null
                            )
                        }
                    }

                    is NetworkResult.OnError -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }

                    is NetworkResult.OnSuccess -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                errorMessage = null,
                                products = result.data
                            )
                        }
                    }
                }
            }
        }
    }


    fun addProduct(product: ProductResponseModelDao) {
        viewModelScope.launch {
            repository.addProduct(product)
        }
    }

    fun deleteProduct(product: ProductResponseModelDao) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

}

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val products: List<ProductResponseModel?>? = null
)