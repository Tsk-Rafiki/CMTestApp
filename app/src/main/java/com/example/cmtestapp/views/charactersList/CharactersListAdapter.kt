package com.example.cmtestapp.views.charactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.views.charactersList.CharactersListFragment.OnCharactersListItemClicked

class CharactersListAdapter(private val onItemClicked: OnCharactersListItemClicked) :
    RecyclerView.Adapter<CharactersListViewHolder>() {
    private var items = listOf<CharactersListViewModelItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharactersListViewHolder(inflater, parent)
    }

    fun updateData(data: CharactersListViewModel) {
        items = data.items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onItemClicked)
    }

}