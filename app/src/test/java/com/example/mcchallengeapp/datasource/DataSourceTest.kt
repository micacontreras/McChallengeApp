package com.example.mcchallengeapp.datasource

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.mcchallengeapp.data.source.ApiServiceDataSource
import com.example.mcchallengeapp.data.source.ApiServiceLocalDataSource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class DataSourceTest {

    lateinit var instrumentationContext: Context

    private lateinit var dataSource: ApiServiceDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        MockitoAnnotations.initMocks(this)
        instrumentationContext = ApplicationProvider.getApplicationContext()

        dataSource = ApiServiceLocalDataSource(instrumentationContext)
    }

    @After
    fun teardown() {
        clearAllMocks()
        unmockkAll()
    }

    // List movies
    @Test
    fun `json with list movies screen response 200`() = runBlocking {
        val response = Result.success(dataSource.getMovies("en"))
        Assert.assertEquals(response.isSuccess, true)
    }

    @Test
    fun `json with searched movie screen response 200`() = runBlocking {
        val response = Result.success(dataSource.searchMovie("alf","en"))
        Assert.assertEquals(response.isSuccess, true)
    }

}
