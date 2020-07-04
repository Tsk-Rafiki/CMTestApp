package com.example.cmtestapp.presenters

import androidx.fragment.app.Fragment
import com.example.cmtestapp.models.charactersRepository.IBaseRepository
import com.example.cmtestapp.models.OnResultListener
import com.example.cmtestapp.views.BaseFragment

abstract class BasePresenter(private val repository: IBaseRepository) : IPresenter,
    OnResultListener {
    protected var view: Fragment? = null

    override fun setView(fragment: BaseFragment<IPresenter>) {
        view = fragment
    }

    override fun onDestroy() {
        repository.removeResultListener(this)
    }
}