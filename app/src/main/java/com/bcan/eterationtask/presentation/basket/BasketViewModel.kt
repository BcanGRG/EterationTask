package com.bcan.eterationtask.presentation.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcan.eterationtask.data.model.ProductResponseModelDao
import com.bcan.eterationtask.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {
    private val _products = MutableStateFlow<List<ProductResponseModelDao>>(emptyList())
    val products = _products.onStart {
        loadProducts()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000),
        initialValue = _products.value
    )

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.combine(_products) { _, products ->
        products.sumOf { product -> product.price.toDouble() * product.quantity }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000),
        initialValue = 0.0
    )


    private fun loadProducts() {
        viewModelScope.launch {
            repository.getBasketProducts().collectLatest {
                _products.value = it
            }
        }
    }

    fun increaseQuantity(productId: String, amount: Int = 1) {
        viewModelScope.launch {
            repository.increaseQuantity(productId, amount)
            loadProducts()
        }
    }

    fun decreaseQuantity(productId: String, amount: Int = 1) {
        viewModelScope.launch {
            repository.decreaseQuantity(productId, amount)
            loadProducts()
        }
    }

}