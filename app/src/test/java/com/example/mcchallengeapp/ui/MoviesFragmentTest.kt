package com.example.mcchallengeapp.ui

import android.os.Looper
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mcchallengeapp.R
import com.example.mcchallengeapp.base.BaseActivityTest
import com.example.mcchallengeapp.data.repository.ApiServiceRepository
import com.example.mcchallengeapp.ui.pages.movies.MoviesFragment
import com.example.mcchallengeapp.utils.LIST_MOVIES_JSON
import dagger.hilt.android.AndroidEntryPoint
import io.mockk.coEvery
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Shadows
import retrofit2.Response

@AndroidEntryPoint
@RunWith(AndroidJUnit4::class)
class MoviesFragmentTest {
    @Mock
    lateinit var repository: ApiServiceRepository

    @get:Rule
    var activityRule = ActivityScenarioRule(BaseActivityTest::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activityRule.scenario.onActivity {
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            it.replaceFragment(MoviesFragment())
        }
    }

    @Test
    fun testMoviesFragment() {
        //coEvery { repository.getMovies("es") } returns Response.success(LIST_MOVIES_JSON)
        onView(withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}