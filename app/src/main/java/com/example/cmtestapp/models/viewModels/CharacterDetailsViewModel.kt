package com.example.cmtestapp.models.viewModels

data class CharacterDetailsViewModel(
    override val id: Int,
    val name: String,
    val playedBy: List<String>,
    val tvSeries: List<String>,
    val born: String,
    val died: String
) : BaseViewModel(id)