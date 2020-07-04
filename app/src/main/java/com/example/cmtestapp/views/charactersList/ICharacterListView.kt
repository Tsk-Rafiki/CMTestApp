package com.example.cmtestapp.views.charactersList

import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.presenters.charactersList.ICharactersListPresenter

interface ICharacterListView {
    fun setData(data: CharactersListViewModel)
    fun setupPresenter(presenter: ICharactersListPresenter)
}
