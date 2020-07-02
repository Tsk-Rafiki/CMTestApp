package com.example.cmtestapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cmtestapp.R
import com.example.cmtestapp.views.characterDetails.CharacterDetailsFragment
import com.example.cmtestapp.views.charactersList.CharactersListFragment

class MainActivity : AppCompatActivity(), CharactersListFragment.OnCharactersListItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val charactersListFragment = CharactersListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragments, charactersListFragment)
            .commit()
    }

    private fun changeFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragments, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.addToBackStack("")
        ft.commit()
    }

    override fun onItemClicked(characterId: Int) {
        changeFragment(CharacterDetailsFragment.newInstance(characterId))
    }
}