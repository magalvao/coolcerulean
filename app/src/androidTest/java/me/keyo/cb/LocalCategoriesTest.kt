package me.keyo.cb

import android.graphics.drawable.Drawable
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.keyo.cb.data.local.CategoriesLocalEndpoint
import me.keyo.cb.util.resourceloader.RemoteResourceLoader
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LocalCategoriesTest {

    private val _appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testLocalCategories() {
        val localCategories = CategoriesLocalEndpoint(_appContext).getCategories()

        // Local categories should never be empty
        Assert.assertFalse(localCategories.isEmpty())

        // All categories should have filled fields
        localCategories.forEach {
            Assert.assertFalse(it.name.isEmpty())
            Assert.assertFalse(it.imagePath.isEmpty())
            Assert.assertFalse(it.tag.isEmpty())
        }
    }

    @Test
    fun testImageLoad() {
        val localCategories = CategoriesLocalEndpoint(_appContext).getCategories()

        localCategories.forEach {
            val image: Drawable? = it.getImageDrawable(_appContext)
            Assert.assertNotNull(image)
        }
    }
}