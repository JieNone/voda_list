package ru.tyurin.vodovoz_test.network.service

import retrofit2.http.GET
import ru.tyurin.vodovoz_test.domain.model.ApiResponse

interface ProductApiService {

    @GET("super_top.php?action=topglav")
    suspend fun searchByUrl(): ApiResponse

}
