package com.cobos.edwin.thesnackapp.home.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.cobos.edwin.thesnackapp.R
import com.cobos.edwin.thesnackapp.api.models.Snack
import com.cobos.edwin.thesnackapp.api.services.AppDatabase
import com.cobos.edwin.thesnackapp.app.App
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).component?.inject(this)

        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {

        appDatabase.snackDao().getAllSnacks().let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Snack>> {
                    override fun onSuccess(t: List<Snack>) {
                        Log.i("ROOM", "Size: " + t.size)
                        Log.i("ROOM", "Size: " + t.toString())
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.i("ROOM", "Disposable: " + d.isDisposed)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("ROOM", "Error: " + e.message)
                    }

                })
        }
/*
        val ob = AppDatabase.getInstance().snackDao().getAllSnacks().let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Snack>> {
                    override fun onSuccess(t: List<Snack>) {
                        Log.i("ROOM", "Size: " + t.size)
                        Log.i("ROOM", "Size: " + t.toString())
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.i("ROOM", "Disposable: " + d.isDisposed)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("ROOM", "Error: " + e.message)
                    }

                })
        }*/
    }
}
