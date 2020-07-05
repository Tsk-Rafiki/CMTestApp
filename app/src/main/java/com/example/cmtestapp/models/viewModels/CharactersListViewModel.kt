package com.example.cmtestapp.models.viewModels

enum class CharactersViewModelTypes {
    DATA,
    LOADING,
    ERROR
}

sealed class BaseViewModel(open val id: Int, val type: Int)

data class CharactersListViewModel(
    override val id: Int,
    val items: List<BaseViewModel>
) : BaseViewModel(id, CharactersViewModelTypes.DATA.ordinal)

data class CharactersListViewModelItem(
    override val id: Int,
    val url: String,
    val name: String,
    val details: String
) : BaseViewModel(id, CharactersViewModelTypes.DATA.ordinal)

data class ErrorViewModel(override val id: Int, val error: String) : BaseViewModel(
    id,
    CharactersViewModelTypes.ERROR.ordinal
)

data class CharacterDetailsViewModel(
    override val id: Int,
    val name: String,
    val playedBy: String,
    val tvSeries: String,
    val born: String,
    val died: String
) : BaseViewModel(id, CharactersViewModelTypes.DATA.ordinal)

data class LoadingViewModel(override val id: Int = 0) :
    BaseViewModel(id, CharactersViewModelTypes.LOADING.ordinal)