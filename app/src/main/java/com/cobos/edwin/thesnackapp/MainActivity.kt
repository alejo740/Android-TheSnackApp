package com.cobos.edwin.thesnackapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.cobos.edwin.thesnackapp.db.AppDatabase
import com.cobos.edwin.thesnackapp.db.Snack
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {

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
        }
    }
}
