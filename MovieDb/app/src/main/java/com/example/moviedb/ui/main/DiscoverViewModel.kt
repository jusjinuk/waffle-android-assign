package com.example.moviedb.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.moviedb.api.Movie
import com.example.moviedb.repository.MovieRepository

class DiscoverViewModel(private val repository: MovieRepository) : ViewModel() {
    fun discoverMovies(page : Long, adapter: MovieListAdapter) {
        repository.discoverMovies(page, adapter)
        Log.i("DiscoverViewModel", "Hello")
    }
}