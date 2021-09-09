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
class RegisterTest {

    @get:Rule
    val testRule = ActivityScenarioRule(RegisterUser::class.java)

    @Test
    fun testLoginUI(){
        onView(withId(R.id.etfirstname))
                .perform(typeText("dilip"))

        closeSoftKeyboard()

        onView(withId(R.id.etlastname))
                .perform(typeText("dilip"))

        closeSoftKeyboard()

        onView(withId(R.id.etemail))
                .perform(typeText("dilip12@gmail.com"))


        closeSoftKeyboard()

        onView(withId(R.id.etpassword))
                .perform(typeText("dilip123"))

        closeSoftKeyboard()

        onView(withId(R.id.etcpassword))
                .perform(typeText("dilip123"))

        closeSoftKeyboard()

        onView(withId(R.id.etmobile))
                .perform(typeText("9868611893"))

        closeSoftKeyboard()

        onView(withId(R.id.btnregister))
                .perform(click())

        Thread.sleep(2000)

        val check = onView(withId(R.id.btnlogin))
                .check(matches(isDisplayed()))
    }

}