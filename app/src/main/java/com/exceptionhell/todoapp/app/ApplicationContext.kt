package com.exceptionhell.todoapp.app

import android.app.Application
import com.exceptionhell.todoapp.BuildConfig
import timber.log.Timber

class ApplicationContext : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}