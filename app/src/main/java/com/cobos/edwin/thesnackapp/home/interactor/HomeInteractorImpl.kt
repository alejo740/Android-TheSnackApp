package com.cobos.edwin.thesnackapp.home.interactor

import com.cobos.edwin.thesnackapp.api.models.Snack
import com.cobos.edwin.thesnackapp.api.services.AppDatabase
import com.cobos.edwin.thesnackapp.home.presenter.HomePresenter.Companion.nonveggie
import com.cobos.edwin.thesnackapp.home.presenter.HomePresenter.Companion.veggie
import io.reactivex.Completable
import io.reactivex.Observable

class HomeInteractorImpl(private val appDatabase: AppDatabase) : HomeInteractor {

    override fun getSnackByType(options: MutableMap<String, Boolean>): Observable<Snack> {
        return appDatabase.snackDao()
            .getAllSnacks()
            .toObservable()
            .flatMap { Observable.fromIterable(it) }
            .filter{x -> checkOption(x, options) }
    }

    private fun checkOption(x: Snack, options: MutableMap<String, Boolean>): Boolean {
        return if(x.isVeggie)
            options[veggie]!!.or(false)
        else
            options[nonveggie]!!.or(false)
    }

    override fun saveNewSnack(snack: Snack): Completable? {
        return Completable.fromAction{ this.appDatabase.snackDao().insertSnack(snack) }
    }
}