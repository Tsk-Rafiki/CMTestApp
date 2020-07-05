package com.example.cmtestapp

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.cmtestapp.views.charactersList.CharactersListViewHolder
import com.example.cmtestapp.views.mock.TestActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CharactersListFragmentTest {
    @get:Rule
    var activityRule =
        object : ActivityTestRule<TestActivity>(TestActivity::class.java, true, false) {
            override fun afterActivityLaunched() {
                val mainHandler = Handler(Looper.getMainLooper())
                mainHandler.post { activity.openCharactersListScreen() }
                super.afterActivityLaunched()
            }
        }

    @Test
    fun checkDataSettedUpInFragment() {
        activityRule.launchActivity(Intent())
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItem<CharactersListViewHolder>(hasDescendant(
            withText("name:1")), click()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition<CharactersListViewHolder>(49))
    }
}