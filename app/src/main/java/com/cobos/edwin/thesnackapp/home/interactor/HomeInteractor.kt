package com.cobos.edwin.thesnackapp.home.interactor

import com.cobos.edwin.thesnackapp.api.models.Snack
import io.reactivex.Observable

interface HomeInteractor {

    fun snackResult(): Observable<Snack>

}