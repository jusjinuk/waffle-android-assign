package com.example.moviedb.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.api.Movie
import com.example.moviedb.repository.MovieRepository

class DiscoverViewModel(private val repository: MovieRepository) : ViewModel() {

    var listMovies = MutableLiveData<MutableList<Movie>>()
    var page: Long = 1

    fun discoverMovies(page : Long) {
        repository.discoverMovies(page)
            ?.doOnSuccess{
                if(listMovies.value == null)
                    listMovies.value = it.results
                else{
                    val tmplist = listMovies.value!!.toMutableList()
                    tmplist.addAll(it.results)
                    listMovies.value = tmplist
                }
            }?.doOnError{
                Log.i("DiscoverViewModel",it.toString())
            }?.subscribe()
    }
}