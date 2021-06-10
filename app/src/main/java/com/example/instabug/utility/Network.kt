package com.example.instabug.utility

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

object Network {
    private const val charsetName = "iso-8859-1"
    private const val websiteURL = "https://instabug.com/"
    fun buildBufferedReader(): BufferedReader {
        val inputStream = URL(websiteURL).openStream()
            ?: throw IOException("No Internet Connection")

        return BufferedReader(InputStreamReader(inputStream, charsetName), 8)
    }
}