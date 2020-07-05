package com.example.cmtestapp.views.mock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cmtestapp.R
import com.example.cmtestapp.models.mock.CharactersRepositoryMock
import com.example.cmtestapp.presenters.mock.CharacterDetailsPresenterMock
import com.example.cmtestapp.presenters.mock.CharactersListPresenterMock
import com.example.cmtestapp.views.Fragments
import com.example.cmtestapp.views.characterDetails.CharacterDetailsFragment
import com.example.cmtestapp.views.charactersList.CharactersListFragment

class TestActivity : AppCompatActivity(), CharactersListFragment.OnCharactersListItemClicked {

    val charactersRepository = CharactersRepositoryMock()
    val charactersListPresenter = CharactersListPresenterMock(charactersRepository)
    val characterDetailsPresenter = CharacterDetailsPresenterMock(charactersRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openCharactersListScreen() {
        val charactersListFragment = CharactersListFragment.newInstance(charactersListPresenter)
        charactersListPresenter.setView(charactersListFragment)
        changeFragment(charactersListFragment, Fragments.CharactersList.name)
    }

    fun openCharacterDetailsScreen(characterId: Int) {
        val characterDetailsFragment =
            CharacterDetailsFragment.newInstance(characterDetailsPresenter, characterId)
        characterDetailsPresenter.setView(characterDetailsFragment)
        changeFragment(characterDetailsFragment, Fragments.CharacterDetails.name)
    }

    private fun changeFragment(fragment: Fragment, name: String) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragments, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.addToBackStack(null)
        ft.commit()
    }


    override fun onItemClicked(characterId: Int) {

    }

}
