package com.example.instabug

import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView = WebView(this)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://instabug.com/")
        webView.zolom()
        val repo = MainRepo().run {
//            GlobalScope.launch {
////                val stream = openSite("https://instabug.com/")
//                Log.e("TAG", "stream $stream")
//                val result: String = stream?.parseBufferedReader() ?: return@launch
//                Log.e("TAG", "result $result")
//                println("------------------------------------")
//
//                val decodedResult = result.decodeHtmlToString()
//                Log.e("TAG", "decodedResult $decodedResult")
        }
    }

    fun WebView.zolom() {
        evaluateJavascript(
            "return document.getElementById(your_id)",
            ValueCallback<String?> {
                // value is your result
            })
    }
}
