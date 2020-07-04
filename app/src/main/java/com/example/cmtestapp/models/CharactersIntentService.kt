package com.example.cmtestapp.models

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.ACTION_RESPONSE
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.RESPONSE_KEY_OUT
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.RESPONSE_TYPE_KEY
import com.example.cmtestapp.models.charactersRepository.CharactersRepository.Companion.URL_KEY
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class CharactersIntentService : IntentService("charactersService") {
    private val TAG = "CharactersIntentService"

    val requestResult = """{[{"url":"https://www.anapioficeandfire.com/api/characters/1","name":"","gender":"Female","culture":"Braavosi","born":"","died":"","titles":[""],"aliases":["The Daughter of the Dusk"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/2","name":"Walder","gender":"Male","culture":"","born":"","died":"","titles":[""],"aliases":["Hodor"],"father":"","mother":"","spouse":"","allegiances":["https://www.anapioficeandfire.com/api/houses/362"],"books":["https://www.anapioficeandfire.com/api/books/1","https://www.anapioficeandfire.com/api/books/2","https://www.anapioficeandfire.com/api/books/3","https://www.anapioficeandfire.com/api/books/5","https://www.anapioficeandfire.com/api/books/8"],"povBooks":[],"tvSeries":["Season 1","Season 2","Season 3","Season 4","Season 6"],"playedBy":["Kristian Nairn"]},{"url":"https://www.anapioficeandfire.com/api/characters/3","name":"","gender":"Male","culture":"","born":"","died":"","titles":[""],"aliases":["Lamprey"],"father":"","mother":"","spouse":"","allegiances":["https://www.anapioficeandfire.com/api/houses/15"],"books":["https://www.anapioficeandfire.com/api/books/3"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/4","name":"","gender":"Female","culture":"Braavosi","born":"","died":"","titles":[""],"aliases":["The Merling Queen"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5","https://www.anapioficeandfire.com/api/books/8"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/5","name":"","gender":"Male","culture":"","born":"","died":"","titles":[""],"aliases":["Old Crackbones"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/6","name":"","gender":"Female","culture":"Braavosi","born":"","died":"","titles":[""],"aliases":["The Poetess"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/7","name":"","gender":"Female","culture":"","born":"","died":"","titles":[""],"aliases":["Porridge"],"father":"","mother":"","spouse":"","allegiances":["https://www.anapioficeandfire.com/api/houses/15"],"books":["https://www.anapioficeandfire.com/api/books/3"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/8","name":"","gender":"Male","culture":"","born":"","died":"","titles":[""],"aliases":["Quickfinger"],"father":"","mother":"","spouse":"","allegiances":["https://www.anapioficeandfire.com/api/houses/23"],"books":["https://www.anapioficeandfire.com/api/books/6"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/9","name":"","gender":"Female","culture":"","born":"","died":"","titles":[""],"aliases":["the Sailor's Wife"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]},{"url":"https://www.anapioficeandfire.com/api/characters/10","name":"","gender":"Female","culture":"Braavosi","born":"","died":"","titles":[""],"aliases":["The Veiled Lady"],"father":"","mother":"","spouse":"","allegiances":[],"books":["https://www.anapioficeandfire.com/api/books/5"],"povBooks":[],"tvSeries":[""],"playedBy":[""]}]}"""

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Got Intent: $intent")
        val type = intent?.getStringExtra(RESPONSE_TYPE_KEY)
        val url = intent?.getStringExtra(URL_KEY)
        val result = url?.let {
            URL(it).run {
                openConnection().run {
                    this as HttpsURLConnection
                    inputStream.bufferedReader().readText()
                }
            }
        }
//        val items = listOf(
//            CharactersListDTO(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTO(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTO(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTO(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTO(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTO(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTO(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen"))
//        )
        result?.let {res ->
            type?.let {t ->
                sendResultWithIntent(res, t)
            }
        }
    }

    private fun sendResultWithIntent(json: String, type: String) {
        val resultIntent = Intent().apply {
            action = ACTION_RESPONSE
            addCategory(Intent.CATEGORY_DEFAULT)
            putExtra(RESPONSE_TYPE_KEY, type)
            putExtra(RESPONSE_KEY_OUT, json)
        }
        sendBroadcast(resultIntent)

    }
}