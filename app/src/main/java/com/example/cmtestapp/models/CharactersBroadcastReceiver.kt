package com.example.cmtestapp.models

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.utils.ResponseDataParser

class CharactersBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val ACTION_RESPONSE = "com.example.cmtestapp.RESPONSE"
        const val RESPONSE_KEY_OUT = "response_key_out"
        const val RESPONSE_TYPE_KEY = "response_type_key"
        const val RESPONSE_LAST_PAGE = "response_last_page"
        enum class ResponseTypeKeys {
            CHARACTERS_LIST_KEY,
            CHARACTER_DETAILS,
            NETWORK_ERROR
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
        val type = intent?.getStringExtra(RESPONSE_TYPE_KEY)
        val lastPage = intent?.getStringExtra(RESPONSE_LAST_PAGE)
        val parsedResult = when(type) {
            ResponseTypeKeys.CHARACTERS_LIST_KEY.name -> ResponseDataParser.parseCharacterListResponse(rawData, lastPage)
            ResponseTypeKeys.CHARACTER_DETAILS.name -> ResponseDataParser.parseCharacterDetailsResponse(rawData)
            ResponseTypeKeys.NETWORK_ERROR.name -> ResponseDataParser.parseNetworkErrorResponse(rawData)
            else -> ResponseDataParser.parseNetworkErrorResponse(rawData)
        }
        repository?.setResult(parsedResult)
    }
}