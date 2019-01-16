package com.cobos.edwin.thesnackapp.app

import android.app.Application
import com.cobos.edwin.thesnackapp.api.services.AppDatabaseModule

class App : Application() {

    var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .appDatabaseModule(AppDatabaseModule(this))
            .build()
    }
}