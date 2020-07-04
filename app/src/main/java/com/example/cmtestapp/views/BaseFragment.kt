package com.example.cmtestapp.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cmtestapp.presenters.IPresenter

abstract class BaseFragment<out T : IPresenter>(open val presenter: T) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.setView(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        Log.d("BaseFragment", "[${this.javaClass.canonicalName}]:onResume()")
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
        Log.d("BaseFragment", "[${this.javaClass.canonicalName}]:onPause()")
    }
}