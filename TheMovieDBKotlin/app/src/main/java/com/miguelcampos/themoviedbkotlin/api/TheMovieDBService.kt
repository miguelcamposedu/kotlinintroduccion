package com.miguelcampos.themoviedbkotlin.api

import com.miguelcampos.themoviedbkotlin.api.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesResponse>
}