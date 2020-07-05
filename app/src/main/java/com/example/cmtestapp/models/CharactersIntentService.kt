package com.example.cmtestapp.models

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.ACTION_RESPONSE
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.RESPONSE_KEY_OUT
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.RESPONSE_LAST_PAGE
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.RESPONSE_TYPE_KEY
import com.example.cmtestapp.models.CharactersBroadcastReceiver.Companion.ResponseTypeKeys.NETWORK_ERROR
import com.example.cmtestapp.models.charactersRepository.CharactersRepository.Companion.URL_KEY
import com.example.cmtestapp.utils.ResponseDataParser.getLastPageNumber
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class CharactersIntentService : IntentService("charactersService") {
    private val TAG = "CharactersIntentService"

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Got Intent: $intent")
        val type = intent?.getStringExtra(RESPONSE_TYPE_KEY)
        val url = intent?.getStringExtra(URL_KEY)
        var lastPageNumber = ""
        val result = url?.let {
            URL(it).run {
                openConnection().run {
                    this as HttpsURLConnection
                    val headers: Map<String, List<String>> = this.getHeaderFields()
                    lastPageNumber = getLastPageNumber(headers["Link"])
                    Log.d(TAG, "Connection object: $this")
                    try {
                        inputStream.bufferedReader().readText()
                    } catch (e: IOException) {
                        Log.d(TAG, e.toString())
                        sendErrorIntent(e.toString())
                        null
                    }
                }
            }
        }
        result?.let {res ->
            type?.let {t ->
                sendResultWithIntent(res, t, lastPageNumber)
            }
        }
    }

    private fun sendErrorIntent(error: String) {
        val resultIntent = Intent().apply {
            action = ACTION_RESPONSE
//            type = NETWORK_ERROR.name
            addCategory(Intent.CATEGORY_DEFAULT)
            putExtra(RESPONSE_TYPE_KEY, NETWORK_ERROR.name)
            putExtra(RESPONSE_KEY_OUT, error)
        }
        sendBroadcast(resultIntent)
    }

    private fun sendResultWithIntent(json: String, type: String, lastPage: String) {
        val resultIntent = Intent().apply {
            action = ACTION_RESPONSE
            addCategory(Intent.CATEGORY_DEFAULT)
            putExtra(RESPONSE_LAST_PAGE, lastPage)
            putExtra(RESPONSE_TYPE_KEY, type)
            putExtra(RESPONSE_KEY_OUT, json)
        }
        sendBroadcast(resultIntent)

    }
}