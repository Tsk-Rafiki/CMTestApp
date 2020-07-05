package com.example.cmtestapp.presenters.charactersList

import android.util.Log
import com.example.cmtestapp.config.Config.CHARACTERS_LIST_LAST_PAGE_DEFAULT_VALUE
import com.example.cmtestapp.config.Config.CHARACTERS_LIST_PAGE_SIZE_DEFAULT_VALUE
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.models.dto.ErrorDTO
import com.example.cmtestapp.models.dto.ICharacterResponse
import com.example.cmtestapp.models.viewModels.*
import com.example.cmtestapp.presenters.BasePresenter
import com.example.cmtestapp.utils.ResponseDataParser.getIdFromUrl
import com.example.cmtestapp.views.charactersList.ICharacterListView

class CharactersListPresenter(private val repository: ICharactersRepository) :
    BasePresenter(repository), ICharactersListPresenter {

    private var pageSize = CHARACTERS_LIST_PAGE_SIZE_DEFAULT_VALUE
    private var pageCount = CHARACTERS_LIST_LAST_PAGE_DEFAULT_VALUE
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
        when(result) {
            is CharactersListDTO -> sendValidData(result)
            is ErrorDTO -> showError(result)
        }
        Log.d("CharactersListPresenter", "Received data: $result")
    }

    private fun sendValidData(res: CharactersListDTO?) {
        if (res?.items?.isNotEmpty() == true)
            currentPage++
        pageCount = res?.lastPage ?: CHARACTERS_LIST_LAST_PAGE_DEFAULT_VALUE
        val result = mutableListOf<BaseViewModel>()
        val viewModel: MutableList<CharactersListViewModelItem> = res?.items?.filter { it.name.isNotBlank() }?.mapIndexed { index, item ->
            CharactersListViewModelItem(
                id = getIdFromUrl(item.url, index),
                url = item.url,
                name = item.name,
                details = item.playedBy[0]
            )
        }?.toMutableList() ?: mutableListOf()
        result.addAll(viewModel)
        if(viewModel.isNotEmpty() && !isLastPage())
            result.add(LoadingViewModel())
        (view as? ICharacterListView)?.setData(CharactersListViewModel(id = 1, items = result.toList()))
    }

    private fun showError(errorDTO: ErrorDTO) {
        (view as? ICharacterListView)?.showError(ErrorViewModel(errorDTO.id, errorDTO.error))
    }
}