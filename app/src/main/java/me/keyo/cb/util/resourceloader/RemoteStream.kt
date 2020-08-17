package me.keyo.cb.util.resourceloader

import android.graphics.BitmapFactory.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class RemoteStream {
    fun getStreamFromURL(source: String): InputStream? {
        return try {
            val url = URL(source)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            connection.getInputStream()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}