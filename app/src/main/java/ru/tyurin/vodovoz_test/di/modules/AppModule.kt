package ru.tyurin.vodovoz_test.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.tyurin.vodovoz_test.data.repository.ProductsRepoImpl
import ru.tyurin.vodovoz_test.domain.repository.ProductsRepo
import ru.tyurin.vodovoz_test.network.service.ProductApiService


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://szorin.vodovoz.ru/newmobile/glavnaya/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
    }

    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): ProductApiService = retrofit.create(ProductApiService::class.java)

    @Provides
    fun provideRepository(
        productApiService: ProductApiService
    ) : ProductsRepo = ProductsRepoImpl(
        productApiService
    )
}