package com.example.mcchallengeapp.viewmodel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mcchallengeapp.data.repository.ApiServiceRepository
import com.example.mcchallengeapp.data.source.SharedPreferencesCustom
import com.example.mcchallengeapp.database.MoviesRepository
import com.example.mcchallengeapp.rules.TestCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [Build.VERSION_CODES.P],
    application = dagger.hilt.android.testing.HiltTestApplication::class
)
class MoviesViewModelTest() {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var hiltrule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: ApiServiceRepository

    @Inject
    lateinit var sharedPreferencesCustom: SharedPreferencesCustom

    @Inject
    lateinit var localDataSource: MoviesRepository

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        hiltrule.inject()
        viewModel = MoviesViewModel(repository, sharedPreferencesCustom, localDataSource)
    }

    @Test
    fun `given the list movies service succeeds, when the user tries to see the screen, then the movies list is shown`() =
        runBlocking {
            viewModel.getTopMovies()

            viewModel.movies.observeForever {
                Assert.assertTrue(!it.isNullOrEmpty())
            }
        }

    @Test
    fun `given the search movies service succeeds, when the user tries to search a movie, then the movies list is shown`() =
        runBlocking {
            viewModel.getSearchedMovie("alf")

            viewModel.movies.observeForever {
                Assert.assertTrue(!it.isNullOrEmpty())
            }
        }
}
