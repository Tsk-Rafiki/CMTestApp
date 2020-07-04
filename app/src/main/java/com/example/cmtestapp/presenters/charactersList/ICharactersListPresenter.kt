package com.example.cmtestapp.presenters.charactersList

import com.example.cmtestapp.presenters.IPresenter

interface ICharactersListPresenter : IPresenter {
    fun setPageSize(size: Int)
    fun isLastPage(): Boolean
    fun resetCurrentPage()
    fun getCharacterList()
}