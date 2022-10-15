package com.example.mcchallengeapp.data.source

import com.example.mcchallengeapp.data.entity.GenericResponse
import retrofit2.Response

interface ApiServiceDataSource {
    suspend fun getMovies(apiKey: String, language: String): Response<GenericResponse>

    suspend fun searchMovie(
        apiKey: String,
        query: String,
        language: String
    ): Response<GenericResponse>
}