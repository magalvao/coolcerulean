package me.keyo.cb.ui.home

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.keyo.cb.base.BaseViewModel
import me.keyo.cb.data.local.CategoriesLocalEndpoint
import me.keyo.cb.data.model.ProductCategory
import me.keyo.cb.util.resourceloader.LocalResourceLoader

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val _application = application

    private val _categories = MutableLiveData<List<ProductCategory>>().apply {
        MutableLiveData<List<ProductCategory>>().also {
            value = loadCategories()
        }
    }

    private fun loadCategories(): List<ProductCategory> = CategoriesLocalEndpoint(_application.baseContext).getCategories()
}