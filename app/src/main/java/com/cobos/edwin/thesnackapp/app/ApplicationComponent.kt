package com.cobos.edwin.thesnackapp.app

import com.cobos.edwin.thesnackapp.api.services.AppDatabaseModule
import com.cobos.edwin.thesnackapp.home.di.HomeModule
import com.cobos.edwin.thesnackapp.home.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, HomeModule::class, AppDatabaseModule::class])
interface ApplicationComponent {

    fun inject(target: MainActivity)

    fun inject(app: App)

}