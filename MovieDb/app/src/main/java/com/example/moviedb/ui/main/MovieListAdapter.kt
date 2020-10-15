package com.example.moviedb.ui.main

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.api.Movie
import com.example.moviedb.databinding.ItemDiscoverMovieBinding
import com.example.moviedb.ui.detail.DetailActivity

class MovieListAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemDiscoverMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }
}

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>(){
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem==newItem
    }

}

class MovieViewHolder(private val binding: ItemDiscoverMovieBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bindItems(movie:Movie){
        binding.run {
            item = movie
            clickListener = MovieClickListener {
                val toDetailIntent: Intent = Intent(binding.root.context, DetailActivity::class.java)
                toDetailIntent.putExtra("item", it)
                binding.root.context.startActivity(toDetailIntent)
            }
            executePendingBindings()
        }
    }
}

class MovieClickListener(val clickListener: (item : Movie) -> Unit){
    fun onClick(item: Movie) = clickListener(item)
}

class InfiniteScrollListener(val layoutManager: GridLayoutManager, val func: () -> Unit):
    RecyclerView.OnScrollListener(){
    private var previousTotal = 0
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            val visibleItemCount = recyclerView.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()


            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (totalItemCount - visibleItemCount)
                <= firstVisibleItem + 2 ) {
                func()
                loading = true
            }
        }
    }
}