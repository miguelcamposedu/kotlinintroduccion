package com.miguelcampos.themoviedbkotlin.api

import com.miguelcampos.themoviedbkotlin.common.Constantes
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInterceptor: Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        // Añadimos parámetros a la URL de la cadena que recibimos (chain)
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constantes.URL_PARAM_APIKEY, Constantes.API_KEY)
            .addQueryParameter(Constantes.URL_PARAM_LANG, Constantes.DEFAULT_LANG)
            .build()

        var request = chain.request()

        request = request?.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)

    }
}