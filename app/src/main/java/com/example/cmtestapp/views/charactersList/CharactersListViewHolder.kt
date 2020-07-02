package com.example.cmtestapp.views.charactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtestapp.R

import com.example.cmtestapp.models.viewModels.CharactersListViewModel

class CharactersListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.character_details_rv_item, parent, false)) {

    private var title: TextView? = itemView.findViewById(R.id.characterName)
    private var description: TextView? = itemView.findViewById(R.id.descriptionText)

    fun bind(viewModel: CharactersListViewModel) {
        title?.text = viewModel.name
        description?.text = viewModel.details
    }
}