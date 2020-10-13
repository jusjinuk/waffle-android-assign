package com.example.moviedb.repository

import android.util.Log
import com.example.moviedb.api.Movie
import com.example.moviedb.api.MovieService
import com.example.moviedb.ui.main.MovieListAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieRepository(private val service: MovieService) {
    fun discoverMovies(page: Long,adapter: MovieListAdapter) {
        service.fetchDiscoverMovie(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                adapter.submitList(it.results)
                Log.i("MovieRepository", "Hello " + it.results[0].title)
            }.doOnError{
                Log.i("MovieRepository", "Error")
            }.subscribe()
    }
}