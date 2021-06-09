package com.example.instabug

import android.webkit.WebView
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val repo = MainRepo().run {
//            val stream = openSite("https://instabug.com/") ?: return
//            val readText: String = stream.readText()
//            stream.close()
//            println(readText)
//            println("------------------------------------")


        }

        assertEquals(4, 2 + 2)
    }
}