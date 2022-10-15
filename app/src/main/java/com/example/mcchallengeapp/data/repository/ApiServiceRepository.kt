package com.example.mcchallengeapp.data.repository

import com.example.mcchallengeapp.data.entity.GenericResponse
import com.example.mcchallengeapp.data.source.ApiServiceDataSource
import com.example.mcchallengeapp.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class ApiServiceRepository @Inject constructor(private val apiService: ApiService) :
    ApiServiceDataSource {

    override suspend fun getMovies(apiKey: String, language: String): Response<GenericResponse> {
        return apiService.getMovies(apiKey, language)
    }

    override suspend fun searchMovie(
        apiKey: String,
        query: String,
        language: String
    ): Response<GenericResponse> {
        return apiService.searchMovie(apiKey, query, language)
    }
}