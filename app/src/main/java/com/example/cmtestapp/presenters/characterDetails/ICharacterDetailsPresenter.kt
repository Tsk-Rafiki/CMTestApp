package com.example.cmtestapp.presenters.characterDetails

import com.example.cmtestapp.presenters.IPresenter

interface ICharacterDetailsPresenter :
    IPresenter {
    fun getCharacterDetails(characterId: Int)
}