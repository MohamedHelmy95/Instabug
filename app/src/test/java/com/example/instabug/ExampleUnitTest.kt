package com.example.instabug

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        val list: List<String?> = listOf("B", "A", "A", "C", "B", "A")

        for (item in list.distinct()) {
            println(item + ": " + Collections.frequency(list, item))
        }
        assertEquals(4, 2 + 2)

    }
}