package com.example.moviedb.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.api.Movie
import com.example.moviedb.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"

        fun intentWithMovie(context: Context, item: Movie): Intent =
            Intent(context, DetailActivity::class.java)
            .apply { putExtra(EXTRA_MOVIE_ID, item) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val item = intent.getParcelableExtra<Movie>(EXTRA_MOVIE_ID)
        binding.run {
            movie = item
            lifecycleOwner=this@DetailActivity
        }

    }
}
