package com.example.cmtestapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cmtestapp.presenters.IPresenter

abstract class BaseFragment<out T : IPresenter>(open val presenter: T) : Fragment() {
//    open var presenter: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.setView(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}