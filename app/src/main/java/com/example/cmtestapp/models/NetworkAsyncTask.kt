package com.example.cmtestapp.models

import android.os.AsyncTask
import android.util.Log
import com.example.cmtestapp.models.dto.CharactersListDTOitem
import com.example.cmtestapp.models.dto.ICharacterResponse
import java.lang.ref.WeakReference

//class NetworkAsyncTask : AsyncTask<String, String, List<CharactersListDTOitem>>() {
//
//    private var listener: WeakReference<OnResultListener?> = WeakReference(null)
//
//    override fun onProgressUpdate(vararg values: String?) {
//        super.onProgressUpdate(*values)
//        Log.d("NetworkAsyncTask", "onProgressUpdate:Thread:${Thread.currentThread().name}")
//    }
//
//    fun setupResultListener(listener: OnResultListener) {
//        this.listener = WeakReference(listener)
//    }
//
//    fun removeResultListener() {
//        listener.clear()
//    }
//
//    override fun onPostExecute(result: List<CharactersListDTOitem>?) {
//        super.onPostExecute(result)
//        Log.d("NetworkAsyncTask", "onPostExecute:Thread:${Thread.currentThread().name}")
//        listener.get()?.let { listener ->
//            result?.let { data ->
//                listener.setResult(data)
//            }
//        }
//    }
//
//    override fun doInBackground(vararg params: String?): List<CharactersListDTOitem> {
//        Log.d("doInBackground", "onPostExecute:Thread:${Thread.currentThread().name}")
//        Thread.sleep(1000)
//        val items = listOf(
//            CharactersListDTOitem(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTOitem(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTOitem(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTOitem(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTOitem(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTOitem(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen")),
//            CharactersListDTOitem(name = "Petyr Baelish", playedBy = listOf("Aidan Gillen"))
//        )
//        return items
//    }
//
//    suspend fun getItem() {
//
//    }
//
//    override fun onCancelled(result: List<CharactersListDTOitem>?) {
//        super.onCancelled(result)
//        Log.d("doInBackground", "onCancelled:Thread:${Thread.currentThread().name}")
//    }
//
//    override fun onCancelled() {
//        super.onCancelled()
//        Log.d("doInBackground", "onCancelled:Thread:${Thread.currentThread().name}")
//    }
//
//    override fun onPreExecute() {
//        super.onPreExecute()
//        Log.d("doInBackground", "onPreExecute:Thread:${Thread.currentThread().name}")
//    }
//
//}
interface OnResultListener {
    fun setResult(result: ICharacterResponse)
}
