package com.example.mcchallengeapp.di

import android.content.Context
import androidx.room.Room
import com.example.mcchallengeapp.data.repository.ApiServiceRepository
import com.example.mcchallengeapp.data.source.ApiServiceDataSource
import com.example.mcchallengeapp.database.DataBase
import com.example.mcchallengeapp.service.ApiService
import com.example.mcchallengeapp.utils.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {
    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext appContext: Context
    ): DataBase {
        return Room.databaseBuilder(
            appContext, DataBase::class.java,
            "mc_data_base"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(db: DataBase) = db.moviesDao()

    //Retrofit
    @Singleton
    @Provides
    fun provideTokenApiServiceRetrofit(
        factory: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    fun provideHttpClientWithoutAuth(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    //Gson
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun bindsApiServiceRepository(
        apiServiceRepository: ApiServiceRepository
    ): ApiServiceDataSource = apiServiceRepository

    @Provides
    @Singleton
    fun createApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}