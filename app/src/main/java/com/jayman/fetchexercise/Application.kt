package com.jayman.fetchexercise

import android.app.Application
import com.jayman.fetchexercise.data.AppContainer
import com.jayman.fetchexercise.data.DefaultAppContainer

class Application : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}