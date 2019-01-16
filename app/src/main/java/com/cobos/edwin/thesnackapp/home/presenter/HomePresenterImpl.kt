package com.cobos.edwin.thesnackapp.home.presenter

import android.util.Log
import com.cobos.edwin.thesnackapp.api.models.Snack
import com.cobos.edwin.thesnackapp.home.interactor.HomeInteractor
import com.cobos.edwin.thesnackapp.home.view.HomeView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomePresenterImpl(private val interactor: HomeInteractor) : HomePresenter {

    private var subscription: Disposable? = null
    private lateinit var view: HomeView

    override fun loadData() {
        subscription = interactor.snackResult()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Snack>() {
                override fun onComplete() {
                    Log.i("ROOM", "COMpleteeee: ")
                }

                override fun onNext(t: Snack) {
                    Log.i("ROOM", "snack: " + t.name)
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


}