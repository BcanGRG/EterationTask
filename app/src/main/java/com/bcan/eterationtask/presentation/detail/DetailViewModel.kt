package com.bcan.eterationtask.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcan.eterationtask.data.domain.model.FavoriteProductEntity
import com.bcan.eterationtask.data.domain.model.ProductEntity
import com.bcan.eterationtask.data.domain.usecase.AddOrRemoveFavoriteUseCase
import com.bcan.eterationtask.data.domain.usecase.AddProductUseCase
import com.bcan.eterationtask.data.domain.usecase.GetFavoritesUseCase
import com.bcan.eterationtask.presentation.ui.snackbar.SnackbarController
import com.bcan.eterationtask.presentation.ui.snackbar.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase,
    private val addOrRemoveFavoriteUseCase: AddOrRemoveFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {


    private val _favorites = MutableStateFlow<List<FavoriteProductEntity?>?>(null)
    val favorites: StateFlow<List<FavoriteProductEntity?>?> = _favorites.onStart {
        getFavorites()
    }.stateIn(
        scope = viewModelScope,
        initialValue = _favorites.value,
        started = SharingStarted.WhileSubscribed(3000)
    )

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
            _favorites.emit(getFavoritesUseCase())
        }
    }

    fun addOrRemoveFavorite(product: FavoriteProductEntity) {
        viewModelScope.launch {
            addOrRemoveFavoriteUseCase(product)
            getFavorites()
        }
    }
}