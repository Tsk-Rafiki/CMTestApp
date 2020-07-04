package com.example.cmtestapp.views

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cmtestapp.R
import com.example.cmtestapp.models.CharactersBroadcastReceiver
import com.example.cmtestapp.models.charactersRepository.CharactersRepository
import com.example.cmtestapp.models.charactersRepository.ICharactersRepository
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
    private lateinit var broadcastReceiver: CharactersBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Game of Thrones characters"
        charactersRepository = CharactersRepository(this)
        openCharactersListScreen()
        registerBroadcastReceiver(charactersRepository)
    }

    private fun registerBroadcastReceiver(charactersRepository: ICharactersRepository) {
        broadcastReceiver = CharactersBroadcastReceiver()
        broadcastReceiver.setupRepository(charactersRepository)
        val intentFilter = IntentFilter(CharactersBroadcastReceiver.ACTION_RESPONSE).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
        }
        registerReceiver(broadcastReceiver, intentFilter)

    }

    private fun openCharactersListScreen() {
        val presenter =
            CharactersListPresenter(
                charactersRepository
            )
        val charactersListFragment = CharactersListFragment.newInstance(presenter)
        presenter.setView(charactersListFragment)
        changeFragment(charactersListFragment, Fragments.CharactersList.name)
    }

    private fun openCharacterDetailsScreen(characterId: Int) {
        val presenter =
            CharacterDetailsPresenter(
                charactersRepository
            )
        val characterDetailsFragment = CharacterDetailsFragment.newInstance(presenter, characterId)
        presenter.setView(characterDetailsFragment)
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
        openCharacterDetailsScreen(characterId)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1) {
            finish()
//            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        charactersRepository.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}