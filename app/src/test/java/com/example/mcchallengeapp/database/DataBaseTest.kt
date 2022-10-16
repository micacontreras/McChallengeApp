package com.example.mcchallengeapp.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.mcchallengeapp.utils.movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Config.OLDEST_SDK])
class DataBaseTest {
    @Mock
    private lateinit var moviesDao: MoviesDao
    private lateinit var db: DataBase

    private var context = ApplicationProvider.getApplicationContext<Context>()

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)

        db = Room.inMemoryDatabaseBuilder(
            context, DataBase::class.java
        ).allowMainThreadQueries().build()
        moviesDao = db.moviesDao()
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()

        db.close()
    }

    @Test
    fun insertMovie() {
        runBlockingTest {
            moviesDao.insertFavouriteMovie(movie)
            val list = moviesDao.getFavouriteMovies()
            list.forEach {
                print(it.id)
            }

            Assert.assertTrue(list.isNotEmpty())
        }
    }
}