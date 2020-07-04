package com.example.cmtestapp.models.viewModels

data class CharactersListViewModelItem(
    override val id: Int,
    val name: String,
    val details: String
) : BaseViewModel(id)
