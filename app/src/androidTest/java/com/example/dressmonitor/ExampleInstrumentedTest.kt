package com.example.dressmonitor

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

// ✅ Import your generated BuildConfig class

private val APPLICATION_ID: Any
    get() {
        TODO()
    }

object BuildConfig {

}

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // ✅ Compare against BuildConfig.APPLICATION_ID
        assertEquals(APPLICATION_ID, appContext.packageName)
    }
}
