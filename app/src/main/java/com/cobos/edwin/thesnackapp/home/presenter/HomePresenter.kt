package com.cobos.edwin.thesnackapp.home.presenter

import com.cobos.edwin.thesnackapp.home.view.HomeView

interface HomePresenter {

    fun loadData()

    fun rxUnsubscribe()

    fun setView(view: HomeView)
}