package com.example.cmtestapp.presenters.charactersList

import android.util.Log
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.models.dto.CharactersListDTOitem
import com.example.cmtestapp.models.dto.ICharacterResponse
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.presenters.BasePresenter
import com.example.cmtestapp.views.charactersList.ICharacterListView

class CharactersListPresenter(private val repository: ICharactersRepository) :
    BasePresenter(repository), ICharactersListPresenter {

    private val pageSize = 50

    init {
        repository.setupResultListener(this)
    }

    override fun getCharacterList(page: Int) {
        repository.getCharacterList(pageSize, page)
    }

    override fun setResult(result: ICharacterResponse) {
        val res = result as? CharactersListDTO
        Log.d("CharactersListPresenter", "Received data: $result")
        val viewModel = res?.items?.mapIndexed { index, item ->
            CharactersListViewModelItem(
                id = index,
                name = item.name ?: "",
                details = item.playedBy?.get(0) ?: ""
            )
        } ?: emptyList()
        (view as? ICharacterListView)?.setData(CharactersListViewModel(id = 1, items = viewModel))
    }

    override fun onDestroy() {
        repository.removeResultListener(this)
    }

}