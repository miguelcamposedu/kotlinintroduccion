package com.miguelcampos.themoviedbkotlin.ui.popularmovies

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.miguelcampos.themoviedbkotlin.R
import com.miguelcampos.themoviedbkotlin.api.response.Movie
import com.miguelcampos.themoviedbkotlin.common.Constantes

import kotlinx.android.synthetic.main.fragment_popular_movie.view.*


class MyPopularMovieRecyclerViewAdapter() : RecyclerView.Adapter<MyPopularMovieRecyclerViewAdapter.ViewHolder>() {

    private var movies: List<Movie> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val movieClicked = v.tag as Movie
            // movieClicked.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_popular_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTitle.text = item.title
        holder.tvRating.text = item.vote_average.toString()

        // Coil Image Library
        holder.ivPhoto.load("${Constantes.IMAGE_BASE_URL}${item.poster_path}") {
            placeholder(R.drawable.ic_cine)
            crossfade(true)
            transformations(CircleCropTransformation())
        }


        holder.mView.tag = movies[position]

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setData(popularMovies: List<Movie>) {
        movies = popularMovies
        // Actualizar la IU con las nuevas películas
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivPhoto: ImageView = mView.image_view_photo
        val tvTitle: TextView = mView.text_view_title
        val tvRating: TextView = mView.text_view_rating
    }
}
