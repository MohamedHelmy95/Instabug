package com.example.instabug

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.instabug.adapter.WordAdapter
import com.example.instabug.core.BaseResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter = WordAdapter()

    private val progress by lazy {
        findViewById<ProgressBar>(R.id.progress_circular)
    }

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter
        lifecycleScope.launchWhenStarted {
            viewModel.response.filterNotNull().collect { response ->
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchViewMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchViewMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_sort) adapter.reSort()
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapter.search(query)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        adapter.search(query)
        return true
    }

}
