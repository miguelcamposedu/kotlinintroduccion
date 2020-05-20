package com.androidavanzado.popcorn.common

import android.content.Context
import android.content.SharedPreferences
import com.androidavanzado.popcorn.di.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        val sharedPref = MyApp.instance?.getSharedPreferences(
            Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        return sharedPref
    }

}