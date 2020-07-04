package com.example.cmtestapp.views

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItems = layoutManager.childCount
        val totalItems = layoutManager.itemCount
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItems + firstVisiblePosition) >= totalItems) {
                loadMoreCharactersData()
            }
        }
    }

    protected abstract fun loadMoreCharactersData()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}