package com.geekbrains.tests.view.search

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.R
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())
        onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))
    }

    @Test
    fun activity_IsDisplayed() {
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_EditTextIsDisplayed() {
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_EditTextTextIsCorrect() {
        onView(withId(R.id.searchEditText)).check(matches(withHint(R.string.search_hint)))
    }

    @Test
    fun activity_ButtonIsDisplayed() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_ButtonTextIsCorrect() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(withText(R.string.to_details)))
    }

    @Test
    fun activity_ButtonIsWorking() {
        onView(withId(R.id.toDetailsActivityButton)).perform(click())
        onView(withId(R.id.activity_details)).check(matches(isDisplayed()))
    }
}