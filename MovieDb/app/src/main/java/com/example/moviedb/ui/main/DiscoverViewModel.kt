package com.example.moviedb.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.api.Movie
import com.example.moviedb.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class DiscoverViewModel(private val repository: MovieRepository) : ViewModel() {

    val listMovies = MutableLiveData<MutableList<Movie>>()
    val errorMessge = MutableLiveData<String>()
    private var page: Long = 1

    private fun discoverMovies(page : Long) {
        repository.discoverMovies(page)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({it ->
                if(listMovies.value == null)
                    listMovies.value = it.results
                else{
                    val tmplist = listMovies.value!!.toMutableList()
                    tmplist.addAll(it.results)
                    listMovies.value = tmplist
                }
            }, {it ->
                it.message?.let { m -> errorMessge.value = m }
            })
    }

    fun incrPage() {
        discoverMovies(page)
        page++
    }
}