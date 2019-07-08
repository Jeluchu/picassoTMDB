package com.jeluchu.picassotmdb.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jeluchu.picassotmdb.R
import com.jeluchu.picassotmdb.data.api.RetrofitClient
import com.jeluchu.picassotmdb.utils.CropSquareTransformation
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import jp.wasabeef.picasso.transformations.GrayscaleTransformation
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.jetbrains.anko.toast
import kotlin.math.roundToInt

class MovieDetailActivity : AppCompatActivity() {

    private val picasso = Picasso.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        configureUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.grayscale -> setGrayScale()
            R.id.cropCircle -> setCropSquareTransformation()
            R.id.blur -> setBlurFilter()
            else -> toast("error")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBlurFilter() {
        picasso
            .load(RetrofitClient.TMDB_IMAGEURL + intent.getStringExtra(MainActivity.POSTER))
            .transform(BlurTransformation(this))
            .error(R.drawable.video_camera)
            .into(detailImageView)
    }

    private fun setGrayScale() {
        picasso
            .load(RetrofitClient.TMDB_IMAGEURL + intent.getStringExtra(MainActivity.POSTER))
            .transform(GrayscaleTransformation())
            .error(R.drawable.video_camera)
            .into(detailImageView)
    }

    private fun setCropSquareTransformation() {
        picasso
            .load(RetrofitClient.TMDB_IMAGEURL + intent.getStringExtra(MainActivity.POSTER))
            .transform(CropSquareTransformation())
            .error(R.drawable.video_camera)
            .into(detailImageView)
    }

    private fun configureUI() {
        titleDetailTextView.text = intent.getStringExtra(MainActivity.TITLE)
        summaryDetailTextView.text = intent.getStringExtra(MainActivity.SUMMARY)
        releaseDateTextView.text = intent.getStringExtra(MainActivity.RELEASE_DATE)
        ratingTextView.text = String.format(getString(R.string.rating), intent.getFloatExtra(MainActivity.RATING, 1f).roundToInt())
        picasso
            .load(RetrofitClient.TMDB_IMAGEURL + intent.getStringExtra(MainActivity.POSTER))
            .error(R.drawable.video_camera)
            .into(detailImageView)
    }

}
