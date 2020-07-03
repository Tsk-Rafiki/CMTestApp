package com.example.cmtestapp.models

import android.util.Log
import com.example.cmtestapp.models.dto.CharactersListDTO


class CharactersRepository(private val networkAsyncTask: NetworkAsyncTask) :
    ICharactersRepository {

    private val baseUrl = "https://www.anapioficeandfire.com/api/"
    private val charactersUrl = baseUrl + "characters"
    private var listeners: MutableList<NetworkAsyncTask.OnResultListener> = mutableListOf()

    init {
        networkAsyncTask.setupResultListener(this)
    }

    override fun getCharacterList(pageSize: Int, page: Int) {
        networkAsyncTask.execute(charactersUrl)
    }

    override fun getCharacterDetails(characterId: Int) {
        networkAsyncTask.execute(charactersUrl + "/${characterId}")
    }

    override fun setupResultListener(listener: NetworkAsyncTask.OnResultListener) {
        listeners.add(listener)
    }

    override fun removeResultListener(listener: NetworkAsyncTask.OnResultListener) {
        listeners.remove(listener)
    }

    override fun onDestroy() {
        networkAsyncTask.removeResultListener()
    }

    override fun getResult(result: List<CharactersListDTO>) {
        Log.d("CharactersRepository", "getResult():Result: $result")
        listeners.forEach {
            it.getResult(result)
        }
    }
}