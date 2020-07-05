package com.example.cmtestapp.presenters.characterDetails

import android.util.Log
import com.example.cmtestapp.config.Config.EMPTY_STRING_HOLDER
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.models.dto.CharacterDetailsDTO
import com.example.cmtestapp.models.dto.ICharacterResponse
import com.example.cmtestapp.models.viewModels.CharacterDetailsViewModel
import com.example.cmtestapp.presenters.BasePresenter
import com.example.cmtestapp.utils.DtoParser.characterDetailsDtoToViewModel
import com.example.cmtestapp.views.characterDetails.ICharacterDetailsView

class CharacterDetailsPresenter(private val repository: ICharactersRepository) :
    BasePresenter(repository), ICharacterDetailsPresenter {

    override fun getCharacterDetails(characterId: Int) {
        repository.getCharacterDetails(characterId)
    }

    override fun setResult(result: ICharacterResponse) {
        val res = result as? CharacterDetailsDTO
        Log.d("CharactersListPresenter", "Received data: $result")
        res?.let {
            val viewModel = characterDetailsDtoToViewModel(it)
            (view as? ICharacterDetailsView)?.setData(viewModel)
        }
    }
}