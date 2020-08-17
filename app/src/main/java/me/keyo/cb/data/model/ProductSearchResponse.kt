package me.keyo.cb.data.model

import com.google.gson.annotations.SerializedName

data class ProductSearchResponse(
    @SerializedName("products")
    val products: List<Product>,

    @SerializedName("currentPage")
    val currentPage: Int,

    @SerializedName("pageSize")
    val pageSize: Int,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("pageCount")
    val pageCount: Int
)