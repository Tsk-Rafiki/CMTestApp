package com.example.cmtestapp.views.charactersList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmtestapp.R
import com.example.cmtestapp.models.viewModels.BaseViewModel

import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.views.charactersList.CharactersListFragment.OnCharactersListItemClicked

class CharactersListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    BaseCharacterListViewHolder(inflater.inflate(R.layout.character_details_rv_item, parent, false)) {

    private var title: TextView? = itemView.findViewById(R.id.characterName)
    private var characterListItem: LinearLayout? = itemView.findViewById(R.id.characterListItem)

    override fun bind(viewModel: BaseViewModel, onItemClicked: OnCharactersListItemClicked) {
        val data = viewModel as? CharactersListViewModelItem
        title?.text = data?.name
        characterListItem?.setOnClickListener {
            onItemClicked.onItemClicked(viewModel.id)
        }
    }
}

abstract class BaseCharacterListViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bind(viewModel: BaseViewModel, onItemClicked: OnCharactersListItemClicked)
}

class LoadingCharacterListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    BaseCharacterListViewHolder(inflater.inflate(R.layout.character_loading_rv_item, parent, false)) {
    private var progress: ProgressBar? = itemView.findViewById(R.id.progressBar)

    override fun bind(viewModel: BaseViewModel, onItemClicked: OnCharactersListItemClicked) {
        progress?.progress = 100
    }
}