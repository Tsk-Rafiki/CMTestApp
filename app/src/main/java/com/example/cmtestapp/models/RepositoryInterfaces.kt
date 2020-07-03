package com.example.cmtestapp.models

import com.example.cmtestapp.models.dto.CharactersListDTO

interface  IBaseRepository {
    fun setupResultListener(listener: NetworkAsyncTask.OnResultListener)
    fun removeResultListener(listener: NetworkAsyncTask.OnResultListener)
}

interface ICharactersRepository : NetworkAsyncTask.OnResultListener, IBaseRepository {
    fun getCharacterList(pageSize: Int, page: Int)
    fun getCharacterDetails(characterId: Int)
    fun onDestroy()
    override fun getResult(result: List<CharactersListDTO>)
}
