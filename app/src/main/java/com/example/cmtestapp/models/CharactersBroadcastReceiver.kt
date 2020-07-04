package com.example.cmtestapp.models

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.utils.ResponseDataParser

class CharactersBroadcastReceiver : BroadcastReceiver() {
    companion object {
        val ACTION_RESPONSE = "com.example.cmtestapp.RESPONSE"
        val RESPONSE_KEY_OUT = "response_key_out"
        val RESPONSE_TYPE_KEY = "response_type_key"
        enum class ResponseTypeKeys {
            CHARACTERS_LIST_KEY,
            CHARACTER_DETAILS
        }
    }
    val TAG = "CharactersBR"
    private var repository: ICharactersRepository? = null

    fun setupRepository(repository: ICharactersRepository) {
        this.repository = repository
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Get data: $intent")
        val rawData = intent?.getStringExtra(RESPONSE_KEY_OUT)
        val parsedResult = when(intent?.getStringExtra(RESPONSE_TYPE_KEY)) {
            ResponseTypeKeys.CHARACTERS_LIST_KEY.name -> ResponseDataParser.parseCharacterListResponse(rawData)
            ResponseTypeKeys.CHARACTER_DETAILS.name -> ResponseDataParser.parseCharacterDetailsResponse(rawData)
            else -> ResponseDataParser.parseCharacterListResponse(rawData)
        }
        repository?.setResult(parsedResult)
    }
}