package com.example.cmtestapp.models.charactersRepository

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.RESPONSE_TYPE_KEY
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.ResponseTypeKeys.*
import com.example.cmtestapp.models.CharactersIntentService
import com.example.cmtestapp.models.OnResultListener
import com.example.cmtestapp.models.dto.ICharacterResponse

class CharactersRepository(private val activity: Activity) : ICharactersRepository {

    private val baseUrl = "https://www.anapioficeandfire.com/api/"
    private val charactersUrl = baseUrl + "characters"
    private val characterDetailsUrl = baseUrl + "characters/"
    private var listeners: MutableList<OnResultListener> = mutableListOf()
    companion object {
        const val URL_KEY = "url_key"
    }

    override fun getCharacterList(pageSize: Int, page: Int) {
        val finalUrl = "$charactersUrl?page=$page&pageSize=$pageSize"
        val charactersListIntent = Intent(activity, CharactersIntentService::class.java).apply {
            putExtra(URL_KEY, finalUrl)
            putExtra(RESPONSE_TYPE_KEY, CHARACTERS_LIST_KEY.name)
        }
        activity.startService(charactersListIntent)
    }

    override fun getCharacterDetails(characterId: Int) {
        val charactersListIntent = Intent(activity, CharactersIntentService::class.java).apply {
            putExtra(URL_KEY, characterDetailsUrl + characterId.toString())
            putExtra(RESPONSE_TYPE_KEY, CHARACTER_DETAILS.name)
        }
        activity.startService(charactersListIntent)
    }

    override fun setupResultListener(listener: OnResultListener) {
        listeners.add(listener)
    }

    override fun removeResultListener(listener: OnResultListener) {
        listeners.remove(listener)
    }

    override fun onDestroy() {
    }

    override fun setResult(result: ICharacterResponse) {
        Log.d("CharactersRepository", "getResult():Result: $result")
        Log.d("CharactersRepository", "Sending to: $listeners")
        listeners.forEach {
            it.setResult(result)
        }
    }
}