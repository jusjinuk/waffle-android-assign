package com.example.moviedb.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun fetchDiscoverMovie(@Query("page") page:Long): Single<DiscoverResponse>
}