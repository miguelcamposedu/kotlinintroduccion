package com.androidavanzado.popcorn.api

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import com.androidavanzado.popcorn.R
import com.androidavanzado.popcorn.common.Constants
import com.androidavanzado.popcorn.di.MyApp
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("url")
    fun provideBaseUrl(): String = Constants.TMDBAPI_BASE_URL

    @Singleton
    @Provides
    @Named("photoBaseUrl")
    fun getImageBaseUrl(): String = Constants.IMAGE_BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(theMovieDBInterceptor: TheMovieDBInterceptor): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            addInterceptor(theMovieDBInterceptor)
            connectTimeout(Constants.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
            build()
        }
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        val sharedPref = MyApp.instance?.getSharedPreferences(
            Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        return sharedPref
    }

    @Singleton
    @Provides
    fun provideTheMovieDBRetrofitService(@Named("url") baseUrl: String, okHttpClient: OkHttpClient): TheMovieDBService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TheMovieDBService::class.java)
    }

}