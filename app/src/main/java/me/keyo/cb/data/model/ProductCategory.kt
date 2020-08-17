package me.keyo.cb.data.model

import android.content.Context
import android.graphics.drawable.Drawable
import me.keyo.cb.util.resourceloader.LocalResourceLoader
import me.keyo.cb.util.resourceloader.RemoteResourceLoader

data class ProductCategory(
    val name: String,
    val tag: String,
    val imagePath: String
) {
    fun getImageDrawable(context: Context): Drawable? {
        //A tilde "~" in the image path means that the image is located in the local resources
        if(imagePath.startsWith("~")) {
            val loader = LocalResourceLoader()
            return loader.getDrawable(imagePath.drop(1), context)
        }

        //If no "~" is present, load as a remote resource
        var loader = RemoteResourceLoader()
        return loader.getDrawable(imagePath, context)
    }
}