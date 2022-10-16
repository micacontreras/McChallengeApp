package com.example.mcchallengeapp.repository

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mcchallengeapp.data.repository.ApiServiceRepository
import com.example.mcchallengeapp.rules.TestCoroutineRule
import com.example.mcchallengeapp.service.ApiService
import com.example.mcchallengeapp.utils.API_KEY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ApiServiceRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var source: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun moviesScreenData_callsDataSource() = runBlockingTest {
        val repository = ApiServiceRepository(source)
        repository.getMovies("en")
        verify(source, times(1)).getMovies(API_KEY, "en")
    }
}
