package com.example.moviedb.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class DiscoverResponse (
    val page: Long,
    val total_results: Long,
    val results: MutableList<Movie>
)

@Parcelize
data class Movie (
    val id: Long,
    val title: String,
    val poster_path: String,
    val overview: String,
    val backdrop_path: String
) : Parcelable