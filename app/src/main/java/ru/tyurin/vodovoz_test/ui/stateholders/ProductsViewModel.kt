package ru.tyurin.vodovoz_test.ui.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.tyurin.vodovoz_test.domain.model.Product
import ru.tyurin.vodovoz_test.domain.repository.ProductsRepo
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repo: ProductsRepo
): ViewModel() {

    init {
        loadProducts()
    }

    private val _productsState = MutableStateFlow<ProductsState>(ProductsState.Loading)

    val productsState: StateFlow<ProductsState> = _productsState

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                val response = repo.getByUrl()
                if (response.status == "Error") {
                    _productsState.value = ProductsState.Error("Ошибка в статусе запроса")
                } else {
                    _productsState.value = ProductsState.Success(response.tovary)
                }
            } catch (e: Exception) {
                _productsState.value = ProductsState.Error("Ошибка загрузки данных")
                e.printStackTrace()
            }
        }
    }
}

sealed class ProductsState {
    data object Loading : ProductsState()
    data class Success(val products: List<Product>) : ProductsState()
    data class Error(val message: String) : ProductsState()
}