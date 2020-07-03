package com.example.cmtestapp.presenters.characterDetails

import com.example.cmtestapp.models.ICharactersRepository
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.presenters.BasePresenter

class CharacterDetailsPresenter(private val repository: ICharactersRepository) :
    BasePresenter(repository), ICharacterDetailsPresenter {

    init {
        repository.setupResultListener(this)
    }

    override fun getCharacterDetails(characterId: Int) {
        repository.getCharacterDetails(characterId)
    }

    override fun getResult(result: List<CharactersListDTO>) {

    }
}