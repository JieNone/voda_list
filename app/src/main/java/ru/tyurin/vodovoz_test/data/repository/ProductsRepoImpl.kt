package ru.tyurin.vodovoz_test.data.repository

import ru.tyurin.vodovoz_test.domain.model.ApiResponse
import ru.tyurin.vodovoz_test.domain.repository.ProductsRepo
import ru.tyurin.vodovoz_test.network.service.ProductApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepoImpl @Inject constructor(
    private val productsApiService: ProductApiService
): ProductsRepo {
    override suspend fun getByUrl(): ApiResponse {
        return productsApiService.searchByUrl()
    }
}