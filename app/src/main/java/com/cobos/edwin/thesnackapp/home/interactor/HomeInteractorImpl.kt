package com.cobos.edwin.thesnackapp.home.interactor

import com.cobos.edwin.thesnackapp.api.models.Snack
import com.cobos.edwin.thesnackapp.api.services.AppDatabase
import io.reactivex.Observable

class HomeInteractorImpl(private val appDatabase: AppDatabase) : HomeInteractor {
    override fun snackResult(): Observable<Snack> {
        return appDatabase.snackDao()
            .getAllSnacks().toObservable().flatMap { Observable.fromIterable(it) }
    }
}