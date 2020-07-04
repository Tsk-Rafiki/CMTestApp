package com.example.cmtestapp.models.charactersRepository

import com.example.cmtestapp.models.OnResultListener
import com.example.cmtestapp.models.dto.ICharacterResponse

interface  IBaseRepository {
    fun setupResultListener(listener: OnResultListener)
    fun removeResultListener(listener: OnResultListener)
}

interface ICharactersRepository : OnResultListener,
    IBaseRepository {
    fun getCharacterList(pageSize: Int, page: Int)
    fun getCharacterDetails(characterId: Int)
    fun onDestroy()
    override fun setResult(result: ICharacterResponse)
}
