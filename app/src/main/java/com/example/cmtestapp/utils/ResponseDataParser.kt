package com.example.cmtestapp.utils

import com.example.cmtestapp.models.dto.CharacterDetailsDTO
import com.example.cmtestapp.models.dto.CharactersListDTO
import com.example.cmtestapp.models.dto.CharactersListDTOitem
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ResponseDataParser  {
    fun parseCharacterListResponse(json: String?): CharactersListDTO {
        if (json == null) return CharactersListDTO()
        val collectionType: Type = object : TypeToken<List<CharactersListDTOitem?>?>() {}.type
        return try {
            CharactersListDTO(items = Gson().fromJson(json, collectionType))
        } catch (e: JsonSyntaxException) {
            CharactersListDTO()
        }
    }

    fun parseCharacterDetailsResponse(json: String?): CharacterDetailsDTO {
        if (json == null) return CharacterDetailsDTO()
//        val collectionType: Type = object : TypeToken<List<CharacterDetailsDTO?>?>() {}.type
        return try {
            Gson().fromJson(json, CharacterDetailsDTO::class.java)
        } catch (e: JsonSyntaxException) {
            CharacterDetailsDTO()
        }
    }
}