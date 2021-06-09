package com.example.instabug

import android.os.Build
import android.text.Html
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object Util {
    fun buildBufferedReader() =
        BufferedReader(
            InputStreamReader(
                URL("https://instabug.com/").openStream(), "iso-8859-1"
            ), 8
        )

    @Suppress("DEPRECATION")
    fun parseBufferedString(input: String): String? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY)?.toString()
        else
            Html.fromHtml(input)?.toString()

}