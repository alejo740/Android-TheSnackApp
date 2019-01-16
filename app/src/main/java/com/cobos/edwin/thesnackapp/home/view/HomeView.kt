package com.cobos.edwin.thesnackapp.home.view

import com.cobos.edwin.thesnackapp.api.models.Snack

interface HomeView {

    fun updateProductList(product: List<Snack>)

    fun showSnackbarError(message: String)

}