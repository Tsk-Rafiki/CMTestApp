package com.example.cmtestapp.views.charactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtestapp.models.viewModels.*
import com.example.cmtestapp.views.charactersList.CharactersListFragment.OnCharactersListItemClicked

class CharactersListAdapter(private val onItemClicked: OnCharactersListItemClicked) :
    RecyclerView.Adapter<BaseCharacterListViewHolder>() {
    private var items = mutableListOf<BaseViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCharacterListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            CharactersViewModelTypes.DATA.ordinal -> CharactersListViewHolder(inflater, parent)
            CharactersViewModelTypes.LOADING.ordinal -> LoadingCharacterListViewHolder(inflater, parent)
            else -> CharactersListViewHolder(inflater, parent)
        }
    }

    fun updateData(data: CharactersListViewModel) {
        val startPosition = items.size + 1
        if(items.size > 0)
            if (items.last() is LoadingViewModel)
                items.removeAt(items.size - 1)
        items.addAll(data.items)
        notifyItemRangeInserted(startPosition, data.items.size)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int) = items[position].hashCode().toLong()

    override fun getItemViewType(position: Int) = items[position].type

    override fun onBindViewHolder(holder: BaseCharacterListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onItemClicked)
    }

}