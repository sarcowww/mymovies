package com.sarco.mymovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.sarco.mymovies.R
import com.sarco.mymovies.databinding.ActivityDetailBinding
import com.sarco.mymovies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "DetailActivity:movie"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if(movie != null){
            supportActionBar?.title = movie.title
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.backdrop_path}")
                .into(binding.ivBackdrop)

            binding.tvSummary.text = movie.overview

            bindDetailInfo(binding.tvDetailInfo, movie)
            binding.fab.setOnClickListener {

            }
        }
    }

    private fun bindDetailInfo(tvDetailInfo: TextView, movie: Movie) {
        tvDetailInfo.text = buildSpannedString {

            appendInfo(R.string.original_language, movie.original_language)
            appendInfo(R.string.original_title, movie.original_title)
            appendInfo(R.string.release_date, movie.release_date)
            appendInfo(R.string.popularity, movie.popularity.toString())
            appendInfo(R.string.vote_average, movie.vote_average.toString())
        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String){
        bold { append("${getString(stringRes)} :")}
        appendLine(value)
    }
}