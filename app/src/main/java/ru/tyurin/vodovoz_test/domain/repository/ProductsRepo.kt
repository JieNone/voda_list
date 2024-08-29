package ru.tyurin.vodovoz_test.domain.repository

import ru.tyurin.vodovoz_test.domain.model.ApiResponse

interface ProductsRepo {

    suspend fun getByUrl(): ApiResponse
}