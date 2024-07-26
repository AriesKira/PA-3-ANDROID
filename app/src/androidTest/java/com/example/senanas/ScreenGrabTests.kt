package com.example.senanas

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.senanas.OldActivities.TestHomeActivity
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.locale.LocaleTestRule

@RunWith(AndroidJUnit4::class)
class ScreenGrabTests {
    companion object {
        @JvmField
        @ClassRule
        val localeTestRule: LocaleTestRule = LocaleTestRule()
    }

    @Test
    fun test_screen_home_activity() {
        ActivityScenario.launch(HomeActivity::class.java)
        Espresso.onView(withId(R.id.home_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Screengrab.screenshot("home_activity_screen")
    }

    @Test
    fun test_screen_tickets_activity() {
        ActivityScenario.launch(TicketsActivity::class.java)
        Espresso.onView(withId(R.id.tickets_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Screengrab.screenshot("tickets_activity_screen")
    }

    @Test
    fun test_screen_profile_activity() {
        ActivityScenario.launch(ProfileActivity::class.java)
        Espresso.onView(withId(R.id.profile_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Screengrab.screenshot("profile_activity_screen")
    }
}