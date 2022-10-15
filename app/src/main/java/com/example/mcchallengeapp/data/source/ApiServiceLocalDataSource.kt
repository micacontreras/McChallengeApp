package com.example.mcchallengeapp.data.source

import android.content.Context
import com.example.mcchallengeapp.data.entity.GenericResponse
import com.example.mcchallengeapp.utils.JsonAssetsUtils
import com.google.gson.Gson
import retrofit2.Response

class ApiServiceLocalDataSource(private val context: Context) : ApiServiceDataSource {
    override suspend fun getMovies(apiKey: String, language: String): Response<GenericResponse> {
        val jsonString = JsonAssetsUtils.getJsonFromAssets(context, "MoviesList.json")
        val result = Gson().fromJson(jsonString, GenericResponse::class.java)
        return Response.success(result)
    }

    override suspend fun searchMovie(
        apiKey: String,
        query: String,
        language: String
    ): Response<GenericResponse> {
        val jsonString = JsonAssetsUtils.getJsonFromAssets(context, "SearchMovieResponse.json")
        val result = Gson().fromJson(jsonString, GenericResponse::class.java)
        return Response.success(result)
    }
}
