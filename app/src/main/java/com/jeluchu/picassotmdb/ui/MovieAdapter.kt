package com.jeluchu.picassotmdb.ui

import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.jeluchu.picassotmdb.R
import com.jeluchu.picassotmdb.data.api.RetrofitClient
import com.jeluchu.picassotmdb.data.model.Movie
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlin.math.roundToInt

class MovieAdapter (private val movieList: List<Movie>, val windowManager: WindowManager, var listener: (Movie?) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) = with(view) {
            titleTextView.text = movie.title
            view.setOnClickListener { listener(movie) }
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val width = (size.x) / 2
            val height = ((size.y) / 2.2).roundToInt()
            val picasso = Picasso.get()
            picasso.setIndicatorsEnabled(true)
            if (movie.posterPath != null) {
                picasso.load(RetrofitClient.TMDB_IMAGEURL + movie.posterPath)
                    .placeholder(R.drawable.video_camera)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_STORE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(width, height)
                    .into(posterImageView)
            } else {
                picasso.load(R.drawable.video_camera)
                    .noFade()
                    .resize(width, height)
                    .into(posterImageView)
            }
        }
    }

}