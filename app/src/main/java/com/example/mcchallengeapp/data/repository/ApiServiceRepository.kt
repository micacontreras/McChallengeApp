package com.example.mcchallengeapp.data.repository

import com.example.mcchallengeapp.data.entity.GenericResponse
import com.example.mcchallengeapp.data.source.ApiServiceDataSource
import com.example.mcchallengeapp.service.ApiService
import com.example.mcchallengeapp.utils.API_KEY
import retrofit2.Response
import javax.inject.Inject

class ApiServiceRepository @Inject constructor(private val apiService: ApiService) :
    ApiServiceDataSource {

    override suspend fun getMovies(language: String): Response<GenericResponse> {
        return apiService.getMovies(API_KEY, language)
    }

    override suspend fun searchMovie(
        query: String,
        language: String
    ): Response<GenericResponse> {
        return apiService.searchMovie(API_KEY, query, language)
    }
}