package com.example.cmtestapp.views.charactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.views.charactersList.CharactersListFragment.OnCharactersListItemClicked

class CharactersListAdapter(private val onItemClicked: OnCharactersListItemClicked) :
    RecyclerView.Adapter<CharactersListViewHolder>() {
    private var items = listOf(
        CharactersListViewModel(0, "Petyr Baelish", "Aidan Gillen"),
        CharactersListViewModel(1, "Petyr Baelish", "Aidan Gillen"),
        CharactersListViewModel(2, "Petyr Baelish", "Aidan Gillen"),
        CharactersListViewModel(3, "Petyr Baelish", "Aidan Gillen")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharactersListViewHolder(inflater, parent)
    }

    fun updateData(data: List<CharactersListViewModel>) {
        items = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onItemClicked)
    }

}