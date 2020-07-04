package com.example.cmtestapp.views.charactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtestapp.R

import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.views.charactersList.CharactersListFragment.OnCharactersListItemClicked

class CharactersListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.character_details_rv_item, parent, false)) {

    private var title: TextView? = itemView.findViewById(R.id.characterName)
    private var characterListItem: LinearLayout? = itemView.findViewById(R.id.characterListItem)

    fun bind(viewModel: CharactersListViewModelItem, onItemClicked: OnCharactersListItemClicked) {
        title?.text = viewModel.name
        characterListItem?.setOnClickListener {
            onItemClicked.onItemClicked(viewModel.id)
        }
    }
}