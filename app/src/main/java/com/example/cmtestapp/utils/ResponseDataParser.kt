package com.example.cmtestapp.utils

import com.example.cmtestapp.models.dto.CharacterDetailsDTO
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.models.dto.CharactersListDTOitem
import com.example.cmtestapp.models.dto.ErrorDTO
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ResponseDataParser {
    fun parseCharacterListResponse(json: String?, lastPage: String?): CharactersListDTO {
        if (json == null) return CharactersListDTO()
        val collectionType: Type = object : TypeToken<List<CharactersListDTOitem?>?>() {}.type
        return try {
            CharactersListDTO(
                items = Gson().fromJson(json, collectionType),
                lastPage = lastPage?.toIntOrNull() ?: 0
            )
        } catch (e: JsonSyntaxException) {
            CharactersListDTO()
        }
    }

    fun parseCharacterDetailsResponse(json: String?): CharacterDetailsDTO {
        if (json == null) return CharacterDetailsDTO()
        return try {
            Gson().fromJson(json, CharacterDetailsDTO::class.java)
        } catch (e: JsonSyntaxException) {
            CharacterDetailsDTO()
        }
    }

    fun parseNetworkErrorResponse(error: String?): ErrorDTO {
        val errorText = if (error.isNullOrEmpty()) "Unknown Error" else error
        return ErrorDTO(1, errorText)
    }

    fun getLastPageNumber(links: List<String>?): String {
        if (links == null) return ""
        val lastLink = links[0].split(", ").last()
        val startIndex = lastLink.indexOf("page=") + 5
        val lastIndex = lastLink.indexOfFirst { it == '&' }
        return lastLink.substring(startIndex until lastIndex)
    }

    fun getIdFromUrl(url: String, defaultValue: Int) =
        url.split('/').last().toIntOrNull() ?: defaultValue
}