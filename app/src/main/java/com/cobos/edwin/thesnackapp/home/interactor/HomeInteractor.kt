package com.cobos.edwin.thesnackapp.home.interactor

import com.cobos.edwin.thesnackapp.api.models.Snack
import io.reactivex.Completable
import io.reactivex.Observable

interface HomeInteractor {

    fun getSnackByType(options: MutableMap<String, Boolean>): Observable<Snack>

    fun saveNewSnack(snack: Snack): Completable?
}