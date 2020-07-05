package com.example.cmtestapp.presenters.mock

import com.example.cmtestapp.models.dto.ICharacterResponse
import com.example.cmtestapp.models.mock.CharactersRepositoryMock
import com.example.cmtestapp.models.viewModels.CharacterDetailsViewModel
import com.example.cmtestapp.presenters.BasePresenter
import com.example.cmtestapp.presenters.characterDetails.ICharacterDetailsPresenter
import com.example.cmtestapp.views.characterDetails.ICharacterDetailsView

class CharacterDetailsPresenterMock(repositoryMock: CharactersRepositoryMock) :
    BasePresenter(repositoryMock), ICharacterDetailsPresenter {



    override fun getCharacterDetails(characterId: Int) {

    }

    override fun onResume() {
        val items = CharacterDetailsViewModel(
            id = 1,
            name ="Jon Snow",
            playedBy = "Kit Harington",
            tvSeries = "1-8",
            born = "2020",
            died = "2020"
        )
        (view as? ICharacterDetailsView)?.setData(items)
    }

    override fun onPause() {}

    override fun setResult(result: ICharacterResponse) {}
}