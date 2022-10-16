package com.example.mcchallengeapp.data.source

import android.content.Context
import com.example.mcchallengeapp.data.entity.GenericResponse
import com.example.mcchallengeapp.utils.JsonAssetsUtils
import com.google.gson.Gson
import retrofit2.Response

class ApiServiceLocalDataSource(private val context: Context) : ApiServiceDataSource {
    override suspend fun getMovies(language: String): Response<GenericResponse> {
        val jsonString = JsonAssetsUtils.getJsonFromAssets(context, "screens/MoviesList.json")
        val result = Gson().fromJson(jsonString, GenericResponse::class.java)
        return Response.success(result)
    }

    override suspend fun searchMovie(
        query: String,
        language: String
    ): Response<GenericResponse> {
        val jsonString = JsonAssetsUtils.getJsonFromAssets(context, "screens/SearchMovieResponse.json")
        val result = Gson().fromJson(jsonString, GenericResponse::class.java)
        return Response.success(result)
    }
}
