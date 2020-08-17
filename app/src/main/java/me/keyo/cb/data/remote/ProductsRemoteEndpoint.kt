package me.keyo.cb.data.remote


import me.keyo.cb.BuildConfig
import me.keyo.cb.data.model.ProductSearchResponse
import me.keyo.cb.data.model.ProductsEndpoint
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsRemoteEndpoint: ProductsEndpoint {

    @GET( BuildConfig.BASE_URL + "ios-assignment/search")
    override fun searchProducts(@Query("query") query: String, @Query("page") page: Int): Call<ProductSearchResponse>
}