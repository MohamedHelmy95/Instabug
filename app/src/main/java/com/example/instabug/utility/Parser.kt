package com.example.instabug.utility

object Parser {
    private val contentRegex = Regex("content=\"(.*?)[\">]")
    fun getTextLines(bodyText: String) = contentRegex.findAll(bodyText)

}