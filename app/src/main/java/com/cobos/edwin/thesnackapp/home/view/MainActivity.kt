package com.cobos.edwin.thesnackapp.home.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cobos.edwin.thesnackapp.R
import com.cobos.edwin.thesnackapp.api.models.Snack
import com.cobos.edwin.thesnackapp.app.App
import com.cobos.edwin.thesnackapp.home.presenter.HomePresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).component?.inject(this)

        setContentView(R.layout.activity_main)

        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData()
    }

    override fun onPause() {
        super.onPause()
        presenter.rxUnsubscribe()
    }

    private fun loadData() {


    }

    override fun updateSnackList(snack: Snack) {

    }

    override fun showSnackbarError(message: String) {

    }
}
