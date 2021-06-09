package com.example.instabug

import android.os.Build
import android.text.Html
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class MainRepo {
    val TAG = "MainRepo"
    private val defaultDispatcher = Dispatchers.IO
     fun openSite(url: String): BufferedReader? =
//        withContext(defaultDispatcher) {
            try {
                BufferedReader(
                    InputStreamReader(
                        URL(url).openStream(),
                        "iso-8859-1"
                    ), 8
                )
            } catch (t: Throwable) {
                null
            }

//        }

    fun BufferedReader.parseBufferedReader(): String {
        val result: StringBuilder = StringBuilder()
        while (readLine() != null) result.append(readLine())
        close()
        return result.toString()
    }

    fun String.decodeHtmlToString(): String? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)?.toString()
        else
        Html.fromHtml(this)?.toString()

    fun mido() {
//        val builder = java.lang.StringBuilder()
//
//        try {
//            val links: Elements = doc.select("a[href]")
//            for (link in links) {
//                builder.append("\n").append("Link : ").append(link.attr("href"))
//                    .append("\n").append("Text : ").append(link.text())
//            }
//        } catch (e: IOException) {
//            builder.append("Error : ").append(e.getMessage()).append("\n")
//        }
    }
}