package com.androidavanzado.popcorn.repository

import android.widget.Toast
import com.androidavanzado.popcorn.api.TheMovieDBService
import com.androidavanzado.popcorn.api.response.APIError
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TheMovieDBRepository @Inject constructor(private var theMovieDBService: TheMovieDBService, private var retrofit: Retrofit) {

    suspend fun getPopularMovies() = theMovieDBService.getPopularMovies()

    suspend fun getPopularPeople() = theMovieDBService.getPopularPeople()

    suspend fun getPersonDetail(idPerson: Int) = theMovieDBService.getPersonDetail(idPerson)

    fun parseError(response: Response<*>): APIError {
        val jsonObject = JSONObject(response.errorBody()!!.string())
        return APIError(jsonObject.getInt("status_code"), jsonObject.getString("status_message"))
    }
}
