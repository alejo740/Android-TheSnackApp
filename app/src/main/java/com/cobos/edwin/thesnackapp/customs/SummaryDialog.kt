package com.cobos.edwin.thesnackapp.customs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.cobos.edwin.thesnackapp.R
import com.cobos.edwin.thesnackapp.api.models.Snack

class SummaryDialog : DialogFragment() {

    internal lateinit var hostListener: AddSnackDialog.SnackDialogListener

    lateinit var selectedSnacks : Array<String>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        retainInstance = true
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Summary")
            builder.setItems(selectedSnacks, object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }

            })
            builder.setPositiveButton(
                R.string.save_button
            ) { _, _ -> hostListener.onDialogPositiveClickSummary()}
            builder.setNegativeButton(
                R.string.cancel_button
            ) { dialog, id ->
                getDialog().dismiss()
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            hostListener = context as AddSnackDialog.SnackDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement SnackDialogListener")
            )
        }
    }
}