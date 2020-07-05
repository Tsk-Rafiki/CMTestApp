package com.example.cmtestapp.presenters.mock

import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.models.dto.ICharacterResponse
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.presenters.BasePresenter
import com.example.cmtestapp.presenters.charactersList.ICharactersListPresenter
import com.example.cmtestapp.views.charactersList.ICharacterListView

class CharactersListPresenterMock(repository: ICharactersRepository) : BasePresenter(repository),
    ICharactersListPresenter {

    var getDetailsCallCounter = 0

    override fun setPageSize(size: Int) {}

    override fun isLastPage() = false

    override fun resetCurrentPage() {}

    override fun getCharacterList() {
        getDetailsCallCounter++
    }

    override fun onResume() {
        val items =(1..50).map {
                CharactersListViewModelItem(
                    id = it,
                    url = "url:$it",
                    name = "name:$it",
                    details = "details:$it"
                )
            }
        (view as? ICharacterListView)?.setData(
            CharactersListViewModel(
                id = 1,
                items = items.toList()
            )
        )
    }

    override fun onPause() {}

    override fun setResult(result: ICharacterResponse) {}
}
