package com.example.mcchallengeapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mcchallengeapp.base.launchFragmentInHiltContainer
import com.example.mcchallengeapp.ui.pages.favourites.FavouritesFragment
import com.example.mcchallengeapp.ui.pages.movies.MoviesFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class FavouriteFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        launchFragmentInHiltContainer<FavouritesFragment>()
    }

    @Test
    fun testLoading() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun testRecycler() {
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.message_empty_list_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
