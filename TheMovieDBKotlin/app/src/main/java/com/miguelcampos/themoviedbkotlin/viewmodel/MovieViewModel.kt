package com.miguelcampos.themoviedbkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miguelcampos.themoviedbkotlin.api.response.Movie
import com.miguelcampos.themoviedbkotlin.repository.TheMovieDBRepository

class MovieViewModel: ViewModel() {
    private var theMovieDBRepository = TheMovieDBRepository()
    private lateinit var popularMovies: LiveData<List<Movie>>

    init {
        // popularMovies = theMovieDBRepository.getAllPopularMovies()
    }

    fun getPopularMovies(): LiveData<List<Movie>> {
        popularMovies = theMovieDBRepository.getAllPopularMovies()
        return popularMovies
    }

}