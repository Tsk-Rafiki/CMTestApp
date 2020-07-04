package com.example.cmtestapp.views.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cmtestapp.R
import com.example.cmtestapp.models.viewModels.CharacterDetailsViewModel
import com.example.cmtestapp.views.BaseFragment
import com.example.cmtestapp.presenters.characterDetails.CharacterDetailsPresenter
import com.example.cmtestapp.presenters.characterDetails.ICharacterDetailsPresenter
import kotlinx.android.synthetic.main.fragment_character_detailes.*

private const val CHARACTER_ID = "characterId"

class CharacterDetailsFragment(presenter: ICharacterDetailsPresenter) :
    BaseFragment<ICharacterDetailsPresenter>(presenter), ICharacterDetailsView {
    private var characterId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(CHARACTER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detailes, container, false)
    }

    override fun onResume() {
        super.onResume()
        characterId?.let {
            presenter.getCharacterDetails(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(presenter: CharacterDetailsPresenter, characterId: Int) =
            CharacterDetailsFragment(presenter)
                .apply {
                    arguments = Bundle().apply {
                        putInt(CHARACTER_ID, characterId)
                    }
                }
    }

    override fun setData(data: CharacterDetailsViewModel) {
        name?.text = data.name
        playedBy?.text = data.playedBy
        appearsInSeasons?.text = data.tvSeries
        born?.text = data.born
        died?.text = data.died
    }
}