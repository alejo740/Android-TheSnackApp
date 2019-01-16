package com.cobos.edwin.thesnackapp.app

import android.app.Application
import com.cobos.edwin.thesnackapp.db.AppDatabase

class App : Application() {

    //var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(applicationContext)
    }
}