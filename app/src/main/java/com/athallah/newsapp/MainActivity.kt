package com.athallah.newsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.athallah.newsapp.adapter.HeadlineAdapter
import com.athallah.newsapp.data.ResultState
import com.athallah.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var headlineAdapter: HeadlineAdapter

    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeHeadline()
    }

    private fun observeHeadline() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.headlineState.collect {result->
                    if (result != null) {
                        showLoading(result is ResultState.Loading)
                        when (result) {
                            is ResultState.Loading -> {}
                            is ResultState.Success -> {
                                val newsData = result.data
                                Log.d("tag", "observeHeadline: $newsData")

                                if (newsData != null) {
                                    headlineAdapter.updateData(newsData)
                                }
                            }
                            is ResultState.Error -> showError()
                        }
                    }
                }
            }
        }
        viewModel.getHeadline()
    }

    private fun showError() {
        binding.rvLatestNews.isVisible = false
    }

    private fun showLoading(isLoading: Boolean) {
        binding.cpiHeadline.isVisible = isLoading
    }

    private fun setupRecyclerView() {
        headlineAdapter = HeadlineAdapter(emptyList())

        binding.rvLatestNews.apply {
            adapter = headlineAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}