package com.example.cmtestapp.presenters.charactersList

import com.example.cmtestapp.models.ICharactersRepository
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.models.viewModels.CharactersListViewModel
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

    override fun getResult(result: List<CharactersListDTO>) {
        val viewModel = result.mapIndexed { index, item ->
            CharactersListViewModel(
                id = index,
                name = item.name,
                details = item.playedBy[0]
            )
        }
        (view as? ICharacterListView)?.setData(viewModel)
    }

    override fun onDestroy() {
        repository.removeResultListener(this)
    }

}