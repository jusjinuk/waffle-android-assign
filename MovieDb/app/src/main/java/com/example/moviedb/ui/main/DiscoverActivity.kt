package com.example.moviedb.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
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
        val movieListAdapter = MovieListAdapter()
        val myLayoutManager = GridLayoutManager(this,2)

        binding.run {
            viewModel = discoverViewModel
            lifecycleOwner = this@DiscoverActivity
            discoverMovieList.adapter = movieListAdapter
            discoverMovieList.layoutManager = myLayoutManager
            discoverMovieList.clearOnScrollListeners()
            discoverMovieList.addOnScrollListener(InfiniteScrollListener(myLayoutManager){
                discoverViewModel.page += 1
                discoverViewModel.discoverMovies(discoverViewModel.page)
            })
        }
        if(discoverViewModel.listMovies.value == null)
            discoverViewModel.discoverMovies(discoverViewModel.page)

    }
}