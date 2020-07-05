package com.example.cmtestapp

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
class CharacterDetailsFragmentTest {
    @get:Rule
    var activityRule =
        object : ActivityTestRule<TestActivity>(TestActivity::class.java, true, false) {
            override fun afterActivityLaunched() {
                val mainHandler = Handler(Looper.getMainLooper())
                mainHandler.post { activity.openCharacterDetailsScreen(1) }
                super.afterActivityLaunched()
            }
        }

    @Test
    fun firstFragmentTest() {
        activityRule.launchActivity(Intent())
        onView(withId(R.id.name)).check(matches(withText("Jon Snow")))
        onView(withId(R.id.playedBy)).check(matches(withText("Kit Harington")))
        onView(withId(R.id.appearsInSeasons)).check(matches(withText("1-8")))
        onView(withId(R.id.born)).check(matches(withText("2020")))
        onView(withId(R.id.died)).check(matches(withText("2020")))
    }
}