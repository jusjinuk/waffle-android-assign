package com.example.moviedb.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityDiscoveryBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverActivity : AppCompatActivity() {
    private val discoverViewModel: DiscoverViewModel by viewModel()
    private lateinit var binding: ActivityDiscoveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_discovery
        )
        Log.i("DiscoverActivity", "Hello")
        binding.run {
            viewModel = discoverViewModel
            lifecycleOwner = this@DiscoverActivity
            discoverMovieList.adapter = MovieListAdapter()
            val adapt = discoverMovieList.adapter as MovieListAdapter
            discoverViewModel.discoverMovies(1,adapt)
        }

    }
}