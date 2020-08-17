package me.keyo.cb.util.resourceloader

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.app.ActivityCompat

class LocalResourceLoader: ResourceLoaderInterface {

    override fun getDrawable(path:String, context: Context): Drawable? {
        //Drawable.createFromStream(javaClass.getResourceAsStream("/drawable/$path"), "") //TODO: Returning null
        val resourceId = context.resources.getIdentifier(path, "drawable", context.packageName)
        return ActivityCompat.getDrawable(context, resourceId)
    }

    override fun getText(path:String, context: Context):String? =
        context.resources.openRawResource(
            context.resources.getIdentifier(path, "raw", context.packageName)
        ).bufferedReader().use { it.readText() }
}