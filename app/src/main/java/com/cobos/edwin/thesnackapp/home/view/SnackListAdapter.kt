package com.cobos.edwin.thesnackapp.home.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cobos.edwin.thesnackapp.R
import com.cobos.edwin.thesnackapp.api.models.Snack

class SnackListAdapter(val clickListener: (Snack, View) -> Unit) :
    RecyclerView.Adapter<SnackListAdapter.SnackViewHolder>() {


    var items: ArrayList<Snack> = ArrayList()
    var linearView: Boolean = true

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SnackViewHolder {
        val layoutId = R.layout.abc_action_menu_item_layout
        val layout = LayoutInflater.from(p0.context).inflate(layoutId, p0, false)
        return SnackViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: SnackViewHolder, p1: Int) {
        p0.bind(items[p1], clickListener)
    }

    fun addCharacter(characterModel: Snack) {
        items.add(characterModel)
        notifyItemInserted(items.size - 1)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun setCharacters(characters: List<Snack>) {
        items.clear()
        items.addAll(characters)
        notifyDataSetChanged()
    }

    class SnackViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        //private var title = view.title_text

        fun bind(character: Snack, clickListener: (Snack, View) -> Unit) {

            //title?.let { it.text = character.title}


            view.setOnClickListener {
                clickListener(character, view)
            }
        }
    }
}