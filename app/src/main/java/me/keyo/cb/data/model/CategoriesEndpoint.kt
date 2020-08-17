package me.keyo.cb.data.model

import me.keyo.cb.data.model.ProductCategory

interface CategoriesEndpoint {
    fun getCategories(): List<ProductCategory>
}