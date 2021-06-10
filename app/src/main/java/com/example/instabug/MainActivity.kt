package com.example.instabug

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.instabug.adapter.WordAdapter
import com.example.instabug.core.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter = WordAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = this@MainActivity.adapter
        }
        val progress = findViewById<ProgressBar>(R.id.progress_circular)

        GlobalScope.launch {
            viewModel.response.filterNotNull().collect { response ->
                withContext(Dispatchers.Main) {
                    when (response) {
                        is BaseResponse.Success -> {
                            adapter.submitList(response.data)
                        }
                        is BaseResponse.Loading -> {
                            val isProgress = response.loading
                            progress.visibility = if (isProgress) View.VISIBLE else View.GONE
                        }
                        is BaseResponse.Error -> {
                            Toast.makeText(
                                this@MainActivity,
                                "${response.throwable.message}",
                                LENGTH_LONG
                            ).show()
                            progress.visibility = View.GONE

                        }
                    }

                }
            }

        }

    }


}
