package com.jeluchu.picassotmdb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jeluchu.picassotmdb.R
import com.jeluchu.picassotmdb.data.MovieInteractor
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "TITLE"
        const val SUMMARY = "SUMMARY"
        const val POSTER = "POSTER"
        const val RELEASE_DATE = "RELEASE_DATE"
        const val RATING = "RATING"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieRecyclerView.layoutManager = GridLayoutManager(this, 2)
        MovieInteractor().getPopularMovies().observe(this, Observer { movieList ->
            if (movieList != null) {
                val adapter = MovieAdapter(movieList, windowManager) { movie ->
                    startActivity(
                        Intent(this@MainActivity, MovieDetailActivity::class.java)
                            .putExtra(TITLE, movie?.title)
                            .putExtra(SUMMARY, movie?.overview)
                            .putExtra(POSTER, movie?.posterPath)
                            .putExtra(RELEASE_DATE, movie?.releaseDate)
                            .putExtra(RATING, movie?.popularity)
                    )
                }
                movieRecyclerView.adapter = adapter
            } else {
                longToast("No movies")
            }
        })
    }
}
