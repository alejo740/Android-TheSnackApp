package com.cobos.edwin.thesnackapp.home.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.cobos.edwin.thesnackapp.R
import com.cobos.edwin.thesnackapp.api.models.Snack
import com.cobos.edwin.thesnackapp.app.App
import com.cobos.edwin.thesnackapp.customs.AddSnackDialog
import com.cobos.edwin.thesnackapp.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HomeView, AddSnackDialog.SnackDialogListener {

    @Inject
    lateinit var presenter: HomePresenter

    private lateinit var adapter: SnackListAdapter

    private val addNewSnackDialog = AddSnackDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).component?.inject(this)

        setContentView(R.layout.activity_main)

        presenter.setView(this)

        setupCheckButtons()
        setupList()
    }

    private fun setupCheckButtons() {
        checkbox_veggie.setOnClickListener(checkButtonsClick())
        checkbox_non_veggie.setOnClickListener(checkButtonsClick())
    }

    private fun checkButtonsClick(): View.OnClickListener {
        return View.OnClickListener {
            if(it.id == R.id.checkbox_veggie){
                checkbox_veggie.isChecked = !checkbox_veggie.isChecked
                presenter.checkVeggie(checkbox_veggie.isChecked)
            }else if(it.id == R.id.checkbox_non_veggie){
                checkbox_non_veggie.isChecked = !checkbox_non_veggie.isChecked
                presenter.checkNonVeggie(checkbox_non_veggie.isChecked)
            }
        }
    }


    private fun setupList() {
        snack_list.layoutManager = LinearLayoutManager(this)
        snack_list.setHasFixedSize(true)
        adapter = SnackListAdapter()
        snack_list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData()
    }

    override fun onPause() {
        super.onPause()
        presenter.rxUnsubscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.add_snack -> {
                addNewSnack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addNewSnack() {
        addNewSnackDialog.show(supportFragmentManager, "somethings")
    }

    override fun updateSnackList(snack: Snack) {
        adapter.addSnack(snack)
    }

    override fun showSnackbarError(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDialogPositiveClick(snack: Snack) {
        presenter.saveNewSnack(snack)
    }

    override fun updateSnackList() {
        presenter.loadData()
    }

    override fun cleanSnackList() {
        adapter.clearItems()
    }
}
