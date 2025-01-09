package com.bcan.eterationtask.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcan.eterationtask.data.domain.model.FavoriteProductEntity
import com.bcan.eterationtask.data.domain.model.ProductEntity
import com.bcan.eterationtask.data.domain.model.ProductResponseModel
import com.bcan.eterationtask.data.domain.usecase.AddOrRemoveFavoriteUseCase
import com.bcan.eterationtask.data.domain.usecase.AddProductUseCase
import com.bcan.eterationtask.data.domain.usecase.GetFavoritesUseCase
import com.bcan.eterationtask.data.repository.ProductsRepository
import com.bcan.eterationtask.data.util.NetworkResult
import com.bcan.eterationtask.presentation.ui.snackbar.SnackbarController
import com.bcan.eterationtask.presentation.ui.snackbar.SnackbarEvent
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
    private val addProductUseCase: AddProductUseCase,
    private val addOrRemoveFavoriteUseCase: AddOrRemoveFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> =
        _uiState.onStart {
            getProducts()
            getFavorites()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
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


    fun addProduct(product: ProductEntity) {
        viewModelScope.launch {
            addProductUseCase(product)
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = "Ürün sepete eklendi.",
                )
            )
        }
    }

    private fun getFavorites() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(favorites = getFavoritesUseCase()) }
        }
    }

    fun addOrRemoveFavorite(product: FavoriteProductEntity) {
        viewModelScope.launch {
            addOrRemoveFavoriteUseCase(product)
            getFavorites()
        }
    }

}

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val products: List<ProductResponseModel?>? = null,
    val favorites: List<FavoriteProductEntity?>? = null
)