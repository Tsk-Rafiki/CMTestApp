package com.example.cmtestapp.presenters

import com.example.cmtestapp.views.BaseFragment

interface IPresenter {
    fun setView(fragment: BaseFragment<IPresenter>)
    fun onDestroy()
}