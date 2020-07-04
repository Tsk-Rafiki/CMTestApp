package com.example.cmtestapp.presenters.charactersList

import android.util.Log
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.models.dto.ICharacterResponse
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.presenters.BasePresenter
import com.example.cmtestapp.views.charactersList.ICharacterListView

class CharactersListPresenter(private val repository: ICharactersRepository) :
    BasePresenter(repository), ICharactersListPresenter {

    private var pageSize = 50
    private var pageCount = 214
    private var currentPage = 1

    override fun setPageSize(size: Int) {
        pageSize = size
    }

    override fun getCharacterList() {
        repository.getCharacterList(pageSize, currentPage)
    }

    override fun isLastPage() = currentPage == pageCount

    override fun resetCurrentPage() {
        currentPage = 1
    }

    override fun setResult(result: ICharacterResponse) {
        val res = result as? CharactersListDTO
        Log.d("CharactersListPresenter", "Received data: $result")
        if (res?.items?.isNotEmpty() == true)
            currentPage++
        val viewModel = res?.items?.filter { it.name.isNotBlank() }?.mapIndexed { index, item ->
            CharactersListViewModelItem(
                id = item.url.split('/').last().toIntOrNull() ?: index,
                url = item.url,
                name = item.name,
                details = item.playedBy[0]
            )
        } ?: emptyList()

        (view as? ICharacterListView)?.setData(CharactersListViewModel(id = 1, items = viewModel))
    }
}