package com.cobos.edwin.thesnackapp.customs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import com.cobos.edwin.thesnackapp.R
import com.cobos.edwin.thesnackapp.api.models.Snack
import kotlinx.android.synthetic.main.dialog_add_snack.*

class AddSnackDialog : DialogFragment() {

    internal lateinit var hostListener: SnackDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        retainInstance = true
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(it.layoutInflater.inflate(R.layout.dialog_add_snack, null))
            builder.setTitle("New Snack")
            builder.setPositiveButton(
                R.string.save_button
            ) { _, _ -> }
            builder.setNegativeButton(
                R.string.cancel_button
            ) { dialog, id ->
                getDialog().dismiss()
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onResume() {
        super.onResume()
        val dialog = dialog as AlertDialog
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val name = dialog.snack_name.text.toString()
                if (name.isNullOrEmpty() || (name.length < 5)) {
                    dialog.snack_name_layout.error = "Please write a valid name!"
                    dialog.snack_name_layout.isErrorEnabled = true
                } else {
                    val type = dialog.snack_type.isChecked
                    val snack = Snack(name = name, isVeggie = type)
                    hostListener.onDialogPositiveClick(snack)
                    dialog.dismiss()
                }
            }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            hostListener = context as SnackDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement SnackDialogListener")
            )
        }
    }

    interface SnackDialogListener {
        fun onDialogPositiveClick(snack: Snack)

        fun onDialogPositiveClickSummary()
    }
}