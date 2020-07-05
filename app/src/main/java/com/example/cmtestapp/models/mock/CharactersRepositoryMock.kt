package com.example.cmtestapp.models.mock

import com.example.cmtestapp.models.OnResultListener
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
import com.example.cmtestapp.models.dto.ICharacterResponse

class CharactersRepositoryMock: ICharactersRepository {
    override fun getCharacterList(pageSize: Int, page: Int) {

    }

    override fun getCharacterDetails(characterId: Int) {

    }

    override fun onDestroy() {

    }

    override fun setResult(result: ICharacterResponse) {

    }

    override fun setupResultListener(listener: OnResultListener) {

    }

    override fun removeResultListener(listener: OnResultListener) {

    }
}