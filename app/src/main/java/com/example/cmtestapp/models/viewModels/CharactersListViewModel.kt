package com.example.cmtestapp.models.viewModels

data class CharactersListViewModel (
    override val id: Int,
    val items: List<CharactersListViewModelItem>
) : BaseViewModel(id)