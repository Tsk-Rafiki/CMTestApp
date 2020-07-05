package com.example.cmtestapp.utils

import com.example.cmtestapp.config.Config
import com.example.cmtestapp.models.dto.CharacterDetailsDTO
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.models.viewModels.BaseViewModel
import com.example.cmtestapp.models.viewModels.CharacterDetailsViewModel
import com.example.cmtestapp.models.viewModels.CharactersListViewModelItem
import com.example.cmtestapp.models.viewModels.LoadingViewModel
import com.example.cmtestapp.utils.ResponseDataParser.getIdFromUrl

object DtoParser {
    fun characterListDtoToViewModel(
        res: CharactersListDTO?,
        isLastPage: Boolean
    ): MutableList<BaseViewModel> {
        val result = mutableListOf<BaseViewModel>()
        val viewModel: MutableList<CharactersListViewModelItem> =
            res?.items?.filter { it.name.isNotBlank() }?.mapIndexed { index, item ->
                CharactersListViewModelItem(
                    id = ResponseDataParser.getIdFromUrl(item.url, index),
                    url = item.url,
                    name = item.name,
                    details = item.playedBy[0]
                )
            }?.toMutableList() ?: mutableListOf()
        result.addAll(viewModel)
        if (viewModel.isNotEmpty() && !isLastPage)
            result.add(LoadingViewModel())
        return result
    }

    fun characterDetailsDtoToViewModel(it: CharacterDetailsDTO) = CharacterDetailsViewModel(
        id = getIdFromUrl(it.url, 1),
        name = handleEmptyString(it.name),
        playedBy = handleEmptyString(it.playedBy.joinToString(", ")),
        tvSeries = handleEmptyString(it.tvSeries.joinToString(", ")),
        born = handleEmptyString(it.born),
        died = handleEmptyString(it.died)
    )


    private fun handleEmptyString(str: String) =
        if (str.isEmpty()) Config.EMPTY_STRING_HOLDER
        else str.trim()

}