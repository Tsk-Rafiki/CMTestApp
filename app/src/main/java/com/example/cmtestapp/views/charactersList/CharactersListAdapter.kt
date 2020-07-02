package com.example.cmtestapp.views.charactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtestapp.models.viewModels.CharactersListViewModel

class CharactersListAdapter : RecyclerView.Adapter<CharactersListViewHolder>() {
    private val items = listOf(
        CharactersListViewModel("Petyr Baelish", "Aidan Gillen"),
        CharactersListViewModel("Petyr Baelish", "Aidan Gillen"),
        CharactersListViewModel("Petyr Baelish", "Aidan Gillen"),
        CharactersListViewModel("Petyr Baelish", "Aidan Gillen")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharactersListViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

}