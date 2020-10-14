package com.example.moviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.api.Movie
import com.example.moviedb.ui.main.MovieListAdapter

@BindingAdapter("setImageUrl")
fun bindImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load("https://image.tmdb.org/t/p/w500/${url}")
        .placeholder(R.drawable.ic_baseline_local_movies_24)
        .into(view)
}

@BindingAdapter("items")
fun bindMovieItems(view: RecyclerView, items: MutableList<Movie>?) {
    val adapt = view.adapter as MovieListAdapter
    if(items != null){
        adapt.submitList(items)
    }
}