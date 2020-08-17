package me.keyo.cb.util.resourceloader

import android.content.Context
import android.graphics.drawable.Drawable

interface ResourceLoaderInterface {

    fun getDrawable(path:String, context: Context):Drawable?
    fun getText(path:String, context: Context):String?

}