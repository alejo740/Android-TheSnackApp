package com.cobos.edwin.thesnackapp.home.view

import com.cobos.edwin.thesnackapp.api.models.Snack

interface HomeView {

    fun updateSnackList(snack: Snack)

    fun showSnackbarError(message: String)

}