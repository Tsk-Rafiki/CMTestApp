package com.example.cmtestapp.presenters

interface IPresenter {
    fun setView(fragment: BaseFragment)
    fun onDestroy()
}