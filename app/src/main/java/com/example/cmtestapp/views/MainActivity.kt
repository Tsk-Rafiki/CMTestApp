package com.example.cmtestapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cmtestapp.R
import com.example.cmtestapp.views.charactersList.CharactersListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val charactersListFragment = CharactersListFragment.newInstance("", "")
        supportFragmentManager.beginTransaction().replace(R.id.fragments, charactersListFragment).commit()
    }
}