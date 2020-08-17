package me.keyo.cb.data.model

import retrofit2.Call

interface ProductsEndpoint {
    fun searchProducts(query: String, page: Int): Call<ProductSearchResponse>
}