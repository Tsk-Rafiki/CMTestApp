package com.example.cmtestapp.presenters

import androidx.fragment.app.Fragment
import com.example.cmtestapp.models.IBaseRepository
import com.example.cmtestapp.models.NetworkAsyncTask

abstract class BasePresenter(private val repository: IBaseRepository) : IPresenter,
    NetworkAsyncTask.OnResultListener {
    protected var view: Fragment? = null

    override fun setView(fragment: BaseFragment) {
        view = fragment
    }

    override fun onDestroy() {
        repository.removeResultListener(this)
    }
}