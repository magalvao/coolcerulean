package me.keyo.cb.data.local

import android.content.Context
import android.graphics.drawable.Drawable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.keyo.cb.data.model.CategoriesEndpoint
import me.keyo.cb.data.model.ProductCategory
import me.keyo.cb.util.resourceloader.LocalResourceLoader
import java.lang.reflect.Array.get
import java.lang.reflect.Type


class CategoriesLocalEndpoint(context: Context): CategoriesEndpoint {

    private val _context = context

    override fun getCategories(): List<ProductCategory> {

        val localLoader = LocalResourceLoader()
        val localJsonText = localLoader.getText("categories", _context)

        val gson = Gson()
        val categoriesListType: Type = object : TypeToken<ArrayList<ProductCategory?>?>() {}.type
        var categories:List<ProductCategory> = gson.fromJson(localJsonText, categoriesListType)

        return categories
    }
}