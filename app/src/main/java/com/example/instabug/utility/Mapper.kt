package com.example.instabug.utility

import android.os.Build
import android.text.Html
import android.util.Log
import com.example.instabug.core.Word
import com.example.instabug.utility.Parser.getTextLines
import java.util.*

object Mapper {
    private const val delimiter = " "

    fun getStringListFromHtml(input: String): List<String> {
        val bodyText = input.substringAfterLast("<body>").substringBeforeLast("</body>")
        Log.e("getStringListFromHtml: ", "body : $bodyText")
        val lines = getTextLines(bodyText = bodyText)
        Log.e("getStringListFromHtml: ", "body : $bodyText")

        val pageContent =
            lines.filter { it.groupValues[1].isNotEmpty() }
                .map { it.groupValues[1] }.toList()
                .joinToString(delimiter)
        Log.e("getStringListFromHtml: ", "pageContent : $pageContent")
        return pageContent.split(delimiter)
    }

    fun getWordList(stringList: List<String>): List<Word> {
        val words = mutableListOf<Word>()
        for (item in stringList.distinct()) words.add(
            Word(
                name = convertHtmlToString(item),
                count = Collections.frequency(stringList, item)
            )
        )
        return words
    }

    @Suppress("DEPRECATION")
    private fun convertHtmlToString(input: String): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY).toString()
        else
            Html.fromHtml(input).toString()

}