package com.example.cmtestapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cmtestapp.R
import com.example.cmtestapp.models.CharactersRepository
import com.example.cmtestapp.models.ICharactersRepository
import com.example.cmtestapp.models.NetworkAsyncTask
import com.example.cmtestapp.presenters.characterDetails.CharacterDetailsPresenter
import com.example.cmtestapp.presenters.charactersList.CharactersListPresenter
import com.example.cmtestapp.views.characterDetails.CharacterDetailsFragment
import com.example.cmtestapp.views.charactersList.CharactersListFragment
import com.example.cmtestapp.views.charactersList.CharactersListFragment.OnCharactersListItemClicked


enum class Fragments {
    CharacterDetails,
    CharactersList
}

class MainActivity : AppCompatActivity(), OnCharactersListItemClicked {
    private lateinit var charactersRepository: ICharactersRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        charactersRepository = CharactersRepository(NetworkAsyncTask())
        openCharactersListScreen()
    }

    private fun openCharactersListScreen() {
        val presenter =
            CharactersListPresenter(
                charactersRepository
            )
        val charactersListFragment = CharactersListFragment.newInstance(presenter)
        changeFragment(charactersListFragment, Fragments.CharactersList.name)
    }

    private fun openCharacterDetailsScreen(characterId: Int) {
        val presenter =
            CharacterDetailsPresenter(
                charactersRepository
            )
        val characterDetailsFragment = CharacterDetailsFragment.newInstance(presenter, characterId)
        changeFragment(characterDetailsFragment, Fragments.CharacterDetails.name)
    }

    private fun changeFragment(fragment: Fragment, name: String) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragments, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.addToBackStack(name)
        ft.commit()
    }

    override fun onItemClicked(characterId: Int) {
        openCharacterDetailsScreen(characterId)
    }

    override fun onDestroy() {
        charactersRepository.onDestroy()
        super.onDestroy()
    }
}