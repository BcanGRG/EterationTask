package com.bcan.eterationtask.presentation.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcan.eterationtask.data.domain.model.ProductResponseModelDao
import com.bcan.eterationtask.data.domain.usecase.DecreaseProductQuantityUseCase
import com.bcan.eterationtask.data.domain.usecase.GetBasketProductsUseCase
import com.bcan.eterationtask.data.domain.usecase.IncreaseProductQuantityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getBasketProductsUseCase: GetBasketProductsUseCase,
    private val increaseProductQuantityUseCase: IncreaseProductQuantityUseCase,
    private val decreaseProductQuantityUseCase: DecreaseProductQuantityUseCase,
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
            _products.value = getBasketProductsUseCase()
        }
    }

    fun increaseQuantity(productId: String, amount: Int = 1) {
        viewModelScope.launch {
            increaseProductQuantityUseCase(productId, amount)
            loadProducts()
        }
    }

    fun decreaseQuantity(productId: String, amount: Int = 1) {
        viewModelScope.launch {
            decreaseProductQuantityUseCase(productId, amount)
            loadProducts()
        }
    }

}