package com.example.cmtestapp.views.charactersList

import com.example.cmtestapp.models.viewModels.CharactersListViewModel
import com.example.cmtestapp.models.viewModels.ErrorViewModel
import com.example.cmtestapp.presenters.charactersList.ICharactersListPresenter

interface ICharacterListView {
    fun setData(data: CharactersListViewModel)
    fun showError(data: ErrorViewModel)
    fun setupPresenter(presenter: ICharactersListPresenter)
}
