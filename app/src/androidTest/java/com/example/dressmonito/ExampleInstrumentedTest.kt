package com.example.dressmonito // Changed to match applicationId

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

// No local BuildConfig stub needed if package name is correct and build generates it.
// The import com.example.dressmonito.BuildConfig will be implicit.

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http.d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // ✅ Compare against the actual BuildConfig.APPLICATION_ID
        assertEquals("com.example.dressmonito", appContext.packageName)
        // You can also assert against the generated BuildConfig
        assertEquals("com.example.dressmonito", com.example.dressmonito.BuildConfig.APPLICATION_ID)
    }
}
