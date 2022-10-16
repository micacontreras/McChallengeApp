package com.example.mcchallengeapp

import android.os.Looper
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.mcchallengeapp.base.BaseActivityTest
import com.example.mcchallengeapp.data.repository.ApiServiceRepository
import com.example.mcchallengeapp.ui.pages.movies.MoviesFragment

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import retrofit2.Response

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {
    //@Mock
    ///lateinit var repository: ApiServiceRepository
    private lateinit var activity: BaseActivityTest

    @get:Rule
    var activityRule = ActivityTestRule(BaseActivityTest::class.java, true, false)

    @Before
    fun setup() {
        activity = activityRule.launchActivity(null)!!

    }
   // @get:Rule
   // var activityRule = ActivityScenarioRule(BaseActivityTest::class.java)
/*
    @Before
    fun setUp() {
        //MockitoAnnotations.initMocks(this)
        activityRule.scenario.onActivity {
            //Shadows.shadowOf(Looper.getMainLooper()).idle()
            it.replaceFragment(MoviesFragment())
        }
    }*/

    @Test
    fun testMoviesFragment() {
        val fragmentScenario = launchFragmentInContainer<MoviesFragment>()

        //coEvery { repository.getMovies("es") } returns Response.success(LIST_MOVIES_JSON)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}