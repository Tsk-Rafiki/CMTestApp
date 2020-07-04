package com.example.cmtestapp.views.charactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.views.charactersList.CharactersListFragment.OnCharactersListItemClicked

class CharactersListAdapter(private val onItemClicked: OnCharactersListItemClicked) :
    RecyclerView.Adapter<CharactersListViewHolder>() {
    private var items = mutableListOf<CharactersListViewModelItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharactersListViewHolder(inflater, parent)
    }

    fun updateData(data: CharactersListViewModel) {
        val startPosition = items.size + 1
        items.addAll(data.items)
        notifyItemRangeInserted(startPosition, data.items.size)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int) = items[position].hashCode().toLong()

    override fun getItemViewType(position: Int) = items[position].hashCode()

    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onItemClicked)
    }

}