package com.example.cmtestapp.views.characterDetails

import com.example.cmtestapp.models.viewModels.CharacterDetailsViewModel
import com.example.cmtestapp.presenters.characterDetails.ICharacterDetailsPresenter

interface ICharacterDetailsView {
    fun setData(data: CharacterDetailsViewModel)
    fun setupPresenter(presenter: ICharacterDetailsPresenter)
}