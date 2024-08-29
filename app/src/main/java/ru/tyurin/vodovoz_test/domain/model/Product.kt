package ru.tyurin.vodovoz_test.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val status: String,
    @SerialName(value = "TOVARY") val tovary: List<Product>
)

@Serializable
data class Product(
    @SerialName(value = "NAME") val name: String?,
    val data: List<DataItem>
)

@Serializable
data class DataItem(
    @SerialName(value = "DETAIL_PICTURE") val picture: String?,
    @SerialName(value = "EXTENDED_PRICE") val price: List<Price>
)

@Serializable
data class Price(
    @SerialName(value = "PRICE")val price: Double
)
