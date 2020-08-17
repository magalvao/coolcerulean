package me.keyo.cb

import android.graphics.drawable.Drawable
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import me.keyo.cb.data.local.CategoriesLocalEndpoint
import me.keyo.cb.util.resourceloader.RemoteResourceLoader
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RemoteImagesTest {

    private val _appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testRemoteImageLoad() {
        val remoteLoader = RemoteResourceLoader()
        val drawable = remoteLoader.getDrawable("https://picsum.photos/200", _appContext)

        Assert.assertNotNull(drawable)
    }
}