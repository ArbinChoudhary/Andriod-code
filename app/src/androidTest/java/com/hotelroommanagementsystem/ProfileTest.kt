package com.hotelroommanagementsystem

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4 :: class)
class ProfileTest {
    @get : Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun ProfileTest() {
        Espresso.onView(ViewMatchers.withId(R.id.fname))
                .perform(ViewActions.typeText("dilip"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.lname))
                .perform(ViewActions.typeText("dilip"))
        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.mobile))
                .perform(ViewActions.typeText("9860611893"))

        Espresso.onView(ViewMatchers.withId(R.id.img))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}