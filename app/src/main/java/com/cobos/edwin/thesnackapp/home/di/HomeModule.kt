package com.cobos.edwin.thesnackapp.home.di

import com.cobos.edwin.thesnackapp.api.services.AppDatabase
import com.cobos.edwin.thesnackapp.home.interactor.HomeInteractor
import com.cobos.edwin.thesnackapp.home.interactor.HomeInteractorImpl
import com.cobos.edwin.thesnackapp.home.presenter.HomePresenter
import com.cobos.edwin.thesnackapp.home.presenter.HomePresenterImpl
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideMainActivityHomePresenter(interactor: HomeInteractor): HomePresenter {
        return HomePresenterImpl(interactor)
    }

    @Provides
    fun provideMainActivityInteractor(appDatabase: AppDatabase): HomeInteractor {
        return HomeInteractorImpl(appDatabase)
    }

}