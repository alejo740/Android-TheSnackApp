package com.cobos.edwin.thesnackapp.home.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import com.cobos.edwin.thesnackapp.R
import com.cobos.edwin.thesnackapp.api.models.Snack
import kotlinx.android.synthetic.main.snack_item.view.*
import java.lang.StringBuilder


class SnackListAdapter() :
    RecyclerView.Adapter<SnackListAdapter.SnackViewHolder>() {

    var items: ArrayList<Snack> = ArrayList()

    var itemsSelectedArray = SparseBooleanArray()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SnackViewHolder {
        val layout = LayoutInflater.from(p0.context).inflate(R.layout.snack_item, p0, false)
        return SnackViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: SnackViewHolder, p1: Int) {
        p0.bind(items[p1], itemsSelectedArray)
    }


    fun addSnack(snack: Snack) {
        items.add(snack)
        notifyItemInserted(items.size)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun setSnacks(snacks: List<Snack>) {
        items.clear()
        items.addAll(snacks)
        notifyDataSetChanged()
    }

    class SnackViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private var item: CheckBox = view.snack_item

        fun bind(snack: Snack, state: SparseBooleanArray) {
            val st = state.get(snack.id, false)
            item.text = StringBuilder().append(snack.id).append(". ").append(snack.name).toString()

            item.isChecked = st

            item.setOnClickListener {
                Log.i("ROOM", ":" + snack.id)
                if (!state.get(snack.id, false)) {
                    item.isChecked = true
                    state.put(snack.id, true)
                } else {
                    item.isChecked = false
                    state.put(snack.id, false)
                }
            }

        }
    }
}