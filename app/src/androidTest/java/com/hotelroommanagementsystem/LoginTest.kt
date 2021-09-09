package com.hotelroommanagementsystem

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class LoginTest {
    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun testLoginUI(){
        onView(withId(R.id.email))
                .perform(typeText("dilip@gmail.com"))
        onView(withId(R.id.password))
                .perform(typeText("dilip123"))
        closeSoftKeyboard()
        onView(withId(R.id.btnlogin))
                .perform(click())
        Thread.sleep(2000)
        val check = onView(withId(R.id.frameLayout))
                .check(matches(isDisplayed()))
    }
}


