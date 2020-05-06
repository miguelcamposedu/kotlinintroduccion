package com.miguelcampos.themoviedbkotlin.api

import com.miguelcampos.themoviedbkotlin.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class TheMovieDBClient {
    private val theMovieDBService: TheMovieDBService
    private val retrofit: Retrofit

    init {
        // Incluir el interceptor que hemos definido

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        val cliente = okHttpClientBuilder.build()

        // Construir el cliente de Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()

        // Instanciamos el servicio de Retrofit a partir del objeto retrofit
        theMovieDBService = retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieDBService() = theMovieDBService

}