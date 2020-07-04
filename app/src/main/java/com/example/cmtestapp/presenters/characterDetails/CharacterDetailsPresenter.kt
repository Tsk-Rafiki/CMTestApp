package com.example.cmtestapp.presenters.characterDetails

import android.util.Log
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.models.dto.CharacterDetailsDTO
import com.example.cmtestapp.models.dto.ICharacterResponse
import com.example.cmtestapp.models.viewModels.CharacterDetailsViewModel
import com.example.cmtestapp.presenters.BasePresenter
import com.example.cmtestapp.views.characterDetails.ICharacterDetailsView
import com.example.cmtestapp.views.charactersList.ICharacterListView

class CharacterDetailsPresenter(private val repository: ICharactersRepository) :
    BasePresenter(repository), ICharacterDetailsPresenter {

    init {
        repository.setupResultListener(this)
    }

    override fun getCharacterDetails(characterId: Int) {
        repository.getCharacterDetails(characterId)
    }

    override fun setResult(result: ICharacterResponse) {
        val res = result as? CharacterDetailsDTO
        Log.d("CharactersListPresenter", "Received data: $result")
        val viewModel = CharacterDetailsViewModel(
            id = 1,
            name = res?.name ?: "",
            details = res?.playedBy?.get(0) ?: ""
        )
        (view as? ICharacterDetailsView)?.setData(viewModel)
    }
}