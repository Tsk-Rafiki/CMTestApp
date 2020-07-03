package com.example.cmtestapp.presenters.charactersList

import com.example.cmtestapp.presenters.IPresenter

interface ICharactersListPresenter : IPresenter {
    fun getCharacterList(page: Int)
}