package com.miguelcampos.themoviedbkotlin.api.response

import com.miguelcampos.themoviedbkotlin.api.response.Movie

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)