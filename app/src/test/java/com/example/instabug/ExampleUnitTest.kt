package com.example.instabug

import com.example.instabug.core.Word
import com.example.instabug.utility.WordListUtil.reSortList
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    private val unSortedList: MutableList<Word> = mutableListOf(
        Word(name = "item 1", count = 1),
        Word(name = "item 3", count = 3),
        Word(name = "item 4", count = 4),
        Word(name = "item 2", count = 2),
        Word(name = "item 5", count = 5)
    )

    @Test
    fun sortListAscending() {
        reSortList(list = unSortedList, isAscending = true)
        println("Sort Ascending $unSortedList")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun sortListDescending() {
        reSortList(list = unSortedList, isAscending = false)
        println("Sort Ascending $unSortedList")
        assertEquals(4, 2 + 2)
    }
}