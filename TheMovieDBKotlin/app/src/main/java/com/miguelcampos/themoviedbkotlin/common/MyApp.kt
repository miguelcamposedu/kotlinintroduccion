package com.miguelcampos.themoviedbkotlin.common

import android.app.Application

class MyApp: Application() {

    // Bloque de código que me permite definir variables estáticas
    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}