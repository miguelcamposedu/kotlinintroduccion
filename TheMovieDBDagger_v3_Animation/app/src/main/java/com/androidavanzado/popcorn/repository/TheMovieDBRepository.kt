package com.androidavanzado.popcorn.repository

import com.androidavanzado.popcorn.api.TheMovieDBService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TheMovieDBRepository @Inject constructor(private var theMovieDBService: TheMovieDBService) {

    suspend fun getPopularMovies() = theMovieDBService.getPopularMovies()

    suspend fun getPopularPeople() = theMovieDBService.getPopularPeople()

    suspend fun getPersonDetail(idPerson: Int) = theMovieDBService.getPersonDetail(idPerson)

}