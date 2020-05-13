package com.androidavanzado.popcorn.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidavanzado.popcorn.repository.TheMovieDBRepository
import com.androidavanzado.popcorn.api.response.Movie
import javax.inject.Inject
import javax.inject.Singleton

//TODO para instanciar el ViewModel, debemos indicar que depende de TheMovieDBRepository

class MovieViewModel @Inject constructor(
    theMovieDBRepository: TheMovieDBRepository
): ViewModel() {
    private var popularMovies: LiveData<List<Movie>> = theMovieDBRepository.popularMovies()

    fun getPopularMovies(): LiveData<List<Movie>> {
        return popularMovies
    }
}