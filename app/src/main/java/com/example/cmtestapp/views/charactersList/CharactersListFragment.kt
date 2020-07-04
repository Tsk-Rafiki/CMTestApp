package com.example.cmtestapp.views.charactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmtestapp.R
import com.example.cmtestapp.models.viewModels.BaseViewModel
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.views.BaseFragment
import com.example.cmtestapp.presenters.charactersList.ICharactersListPresenter
import kotlinx.android.synthetic.main.fragment_characters_list.view.*

class CharactersListFragment(presenter: ICharactersListPresenter) : BaseFragment<ICharactersListPresenter>(presenter), ICharacterListView {
    private lateinit var adapter: CharactersListAdapter
//    override var presenter: ICharactersListPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_characters_list, container, false)
        val clickListener = this.activity as OnCharactersListItemClicked
        adapter = CharactersListAdapter(clickListener)
        rootView.recyclerView?.layoutManager = LinearLayoutManager(this.context)
        rootView.recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
        return rootView
    }

    override fun setData(data: CharactersListViewModel) {
        adapter.updateData(data)
        adapter.notifyDataSetChanged()
    }

    override fun setupPresenter(presenter: ICharactersListPresenter) {
//        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        presenter.getCharacterList(1)
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter: ICharactersListPresenter) = CharactersListFragment(presenter)
    }

    interface OnCharactersListItemClicked {
        fun onItemClicked(characterId: Int)
    }
}