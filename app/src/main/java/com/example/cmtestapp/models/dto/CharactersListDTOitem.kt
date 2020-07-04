package com.example.cmtestapp.models.dto

data class CharactersListDTOitem(
    val aliases: List<String> = emptyList(),
    val allegiances: List<Any> = emptyList(),
    val books: List<String> = emptyList(),
    val born: String = "",
    val culture: String = "",
    val died: String = "",
    val father: String = "",
    val gender: String = "",
    val mother: String = "",
    val name: String = "",
    val playedBy: List<String> = emptyList(),
    val povBooks: List<Any> = emptyList(),
    val spouse: String = "",
    val titles: List<String> = emptyList(),
    val tvSeries: List<String> = emptyList(),
    val url: String = ""
)