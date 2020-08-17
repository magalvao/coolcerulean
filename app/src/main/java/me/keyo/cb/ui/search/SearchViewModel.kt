package me.keyo.cb.ui.search

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.keyo.cb.base.BaseViewModel
import me.keyo.cb.data.local.CategoriesLocalEndpoint
import me.keyo.cb.data.model.Product
import me.keyo.cb.data.model.ProductCategory
import me.keyo.cb.data.model.ProductSearchResponse
import me.keyo.cb.data.remote.ProductsRemoteEndpoint
import me.keyo.cb.data.remote.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class SearchViewModel(application: Application) : BaseViewModel(application) {

    fun performSearch(searchTerm: String?, page: Int = 1): LiveData<ProductSearchResponse> {
        val request = ServiceBuilder.buildService(ProductsRemoteEndpoint::class.java)
        val call = request.searchProducts(searchTerm.orEmpty(), page)
        var data = MutableLiveData<ProductSearchResponse>()

        call.enqueue(object : Callback<ProductSearchResponse>{
            override fun onFailure(call: Call<ProductSearchResponse>, t: Throwable) {
                println(t.message)
                Toast.makeText(_application.baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ProductSearchResponse>,
                response: Response<ProductSearchResponse>
            ) {
                data.value = response.body()
            }

        })

        return data
    }

    private val _application = application





}