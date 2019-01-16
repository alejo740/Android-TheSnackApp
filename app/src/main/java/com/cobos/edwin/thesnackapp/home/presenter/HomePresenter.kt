package com.cobos.edwin.thesnackapp.home.presenter

import com.cobos.edwin.thesnackapp.api.models.Snack
import com.cobos.edwin.thesnackapp.home.view.HomeView

interface HomePresenter {

    companion object {
        const val veggie : String = "veggie"
        const val nonveggie : String = "nonveggie "
    }

    fun loadData()

    fun rxUnsubscribe()

    fun setView(view: HomeView)

    fun checkVeggie(value: Boolean)

    fun checkNonVeggie(value: Boolean)

    fun saveNewSnack(snack: Snack)
}