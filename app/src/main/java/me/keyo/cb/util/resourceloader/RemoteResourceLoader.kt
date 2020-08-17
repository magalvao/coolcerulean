package me.keyo.cb.util.resourceloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class RemoteResourceLoader: ResourceLoaderInterface {
    override fun getDrawable(path: String, context: Context): Drawable? {
        val stream = RemoteStream()
        val bitmap: Bitmap? = BitmapFactory.decodeStream(stream.getStreamFromURL(path))
        val drawable: Drawable? = BitmapDrawable(context.resources, bitmap)
        return drawable
    }

    override fun getText(path: String, context: Context): String {
        TODO("Not yet implemented")
    }
}