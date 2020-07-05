package com.example.cmtestapp.presenters.characterDetails

import android.util.Log
import com.example.cmtestapp.config.Config.EMPTY_STRING_HOLDER
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.models.dto.CharacterDetailsDTO
import com.example.cmtestapp.models.dto.ICharacterResponse
import com.example.cmtestapp.models.viewModels.CharacterDetailsViewModel
import com.example.cmtestapp.presenters.BasePresenter
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
            val viewModel = CharacterDetailsViewModel(
                id = it.url.split('/').last().toIntOrNull() ?: 1,
                name = handleEmptyString(it.name),
                playedBy = handleEmptyString(it.playedBy.joinToString(", ")),
                tvSeries = (it.tvSeries.joinToString(", ")),
                born = handleEmptyString(it.born),
                died = handleEmptyString(it.died)
            )
            (view as? ICharacterDetailsView)?.setData(viewModel)
        }
    }

    private fun handleEmptyString(str: String) =
        if (str.isEmpty()) EMPTY_STRING_HOLDER
        else str.trim()
}