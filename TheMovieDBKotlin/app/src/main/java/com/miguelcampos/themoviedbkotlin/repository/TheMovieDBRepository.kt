package com.miguelcampos.themoviedbkotlin.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.miguelcampos.themoviedbkotlin.api.TheMovieDBClient
import com.miguelcampos.themoviedbkotlin.api.TheMovieDBService
import com.miguelcampos.themoviedbkotlin.api.response.Movie
import com.miguelcampos.themoviedbkotlin.api.response.PopularMoviesResponse
import com.miguelcampos.themoviedbkotlin.common.MyApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDBRepository {
    var theMovieDBService: TheMovieDBService
    var theMovieDBClient: TheMovieDBClient = TheMovieDBClient()
    var popularMovies: MutableLiveData<List<Movie>>

    init {
        theMovieDBService = theMovieDBClient.getTheMovieDBService()
        popularMovies = MutableLiveData<List<Movie>>()
    }

    fun getAllPopularMovies(): MutableLiveData<List<Movie>> {

        val call: Call<PopularMoviesResponse> = theMovieDBService.getPopularMovies()
        call.enqueue(object: Callback<PopularMoviesResponse> {
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if(response.isSuccessful) {
                    popularMovies.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
               Toast.makeText(MyApp.instance, "Error en popular movies", Toast.LENGTH_LONG).show()
            }
        })

        return popularMovies
    }
}