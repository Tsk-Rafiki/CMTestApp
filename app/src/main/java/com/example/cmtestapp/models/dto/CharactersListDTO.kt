package com.example.cmtestapp.models.dto

class CharactersListDTO (
    val lastPage: Int = 0,
    val items: List<CharactersListDTOitem> = emptyList()
) : ICharacterResponse