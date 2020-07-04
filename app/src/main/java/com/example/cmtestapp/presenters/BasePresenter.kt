package com.example.cmtestapp.presenters

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.cmtestapp.models.charactersRepository.IBaseRepository
import com.example.cmtestapp.models.OnResultListener
import com.example.cmtestapp.views.BaseFragment

abstract class BasePresenter(private val repository: IBaseRepository) : IPresenter,
    OnResultListener {
    protected var view: Fragment? = null

    override fun setView(fragment: BaseFragment<IPresenter>) {
        Log.d("BasePresenter", "[${this.javaClass.canonicalName}]:setView()")
        view = fragment
    }

    override fun onResume() {
        Log.d("BasePresenter", "[${this.javaClass.canonicalName}]:onResume()")
        repository.setupResultListener(this)
    }

    override fun onPause() {
        Log.d("BasePresenter", "[${this.javaClass.canonicalName}]:onPause()")
        repository.removeResultListener(this)
    }
}