package com.cobos.edwin.thesnackapp.home.presenter

import android.util.Log
import com.cobos.edwin.thesnackapp.api.models.Snack
import com.cobos.edwin.thesnackapp.home.interactor.HomeInteractor
import com.cobos.edwin.thesnackapp.home.presenter.HomePresenter.Companion.nonveggie
import com.cobos.edwin.thesnackapp.home.presenter.HomePresenter.Companion.veggie
import com.cobos.edwin.thesnackapp.home.view.HomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomePresenterImpl(private val interactor: HomeInteractor) : HomePresenter {

    private var subscription: Disposable? = null
    private lateinit var view: HomeView
    val options: MutableMap<String, Boolean> = mutableMapOf(Pair(veggie, false), Pair(nonveggie, false))

    override fun loadData() {
        view.cleanSnackList()
        subscription = interactor.getSnackByType(this.options)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Snack>() {
                override fun onComplete() {
                    Log.i("ROOM", "COMpleteeee: ")
                }

                override fun onNext(t: Snack) {
                    Log.i("ROOM", "snack: " + t.name + " id: "+t.id)
                    view.updateSnackList(t)

                }

                override fun onError(e: Throwable) {
                    Log.e("ROOM", "Error: " + e.message)
                    view.showSnackbarError("Error getting Snacks")
                }

            })
    }



    override fun rxUnsubscribe() {
        subscription?.let {
            if (it.isDisposed)
                it.dispose()
        }
    }

    override fun setView(view: HomeView) {
        this.view = view
    }

    override fun checkVeggie(value: Boolean) {
        options[veggie] = value
        loadData()
    }

    override fun checkNonVeggie(value: Boolean) {
        options[nonveggie] = value
        loadData()
    }

    override fun saveNewSnack(snack: Snack) {
        val result = interactor.saveNewSnack(snack)!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver(){
            override fun onComplete() {
                view.showSnackbarError("Success!")
                view.updateSnackList()
            }

            override fun onError(e: Throwable) {
                Log.e("ROOM", e.message)
                view.showSnackbarError("Sorry Snack not saved!")
            }

        })
    }
}