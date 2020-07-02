package com.example.cmtestapp.views.charactersList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmtestapp.R
import kotlinx.android.synthetic.main.fragment_characters_list.*
import kotlinx.android.synthetic.main.fragment_characters_list.view.*

class CharactersListFragment : Fragment() {
    private lateinit var adapter: CharactersListAdapter

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

    companion object {
        @JvmStatic
        fun newInstance() = CharactersListFragment()
    }

    interface OnCharactersListItemClicked {
        fun onItemClicked(characterId: Int)
    }
}