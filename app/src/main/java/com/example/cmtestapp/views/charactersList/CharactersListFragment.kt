package com.example.cmtestapp.views.charactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.example.cmtestapp.R
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.models.viewModels.ErrorViewModel
import com.example.cmtestapp.views.BaseFragment
import com.example.cmtestapp.presenters.charactersList.ICharactersListPresenter
import com.example.cmtestapp.views.PaginationListener
import kotlinx.android.synthetic.main.fragment_characters_list.*

class CharactersListFragment : BaseFragment<ICharactersListPresenter>(), ICharacterListView {
    private lateinit var adapter: CharactersListAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var isLoading: Boolean = false
    override lateinit var presenter: ICharactersListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        layoutManager = LinearLayoutManager(this.context)
        adapter = CharactersListAdapter(this.activity as OnCharactersListItemClicked).apply {
            setHasStableIds(true)
        }
        adapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        presenter.getCharacterList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView(recyclerView, layoutManager, adapter)
    }

    private fun configureRecyclerView(
        rv: RecyclerView?,
        layoutManager: LinearLayoutManager,
        adapter: CharactersListAdapter
    ) {
        rv?.layoutManager = layoutManager
        rv?.adapter = adapter
        recyclerView?.addOnScrollListener(object : PaginationListener(layoutManager) {
            override fun loadMoreCharactersData() {
                isLoading = true
                presenter.getCharacterList()
            }
            override fun isLastPage() = presenter.isLastPage()
            override fun isLoading() = isLoading
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView?.layoutManager = null
    }

    override fun setData(data: CharactersListViewModel) {
        isLoading = false
        adapter.updateData(data)
        adapter.notifyDataSetChanged()
    }

    override fun showError(data: ErrorViewModel) {
        context?.let {
            val dialog = AlertDialog.Builder(it).apply {
                setMessage(data.error)
                setCancelable(true)
                setTitle("Error")
                setPositiveButton("Ok") { dialog, _ -> dialog.cancel() }
            }.create()
            dialog.show()
        }
    }

    override fun setupPresenter(presenter: ICharactersListPresenter) {
        this.presenter = presenter
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersListFragment()
    }

    interface OnCharactersListItemClicked {
        fun onItemClicked(characterId: Int)
    }
}