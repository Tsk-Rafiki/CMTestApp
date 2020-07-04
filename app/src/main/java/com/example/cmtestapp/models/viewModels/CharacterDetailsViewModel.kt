package com.example.cmtestapp.models.viewModels

data class CharacterDetailsViewModel(override val id: Int, val name: String, val details: String) :
    BaseViewModel(id)