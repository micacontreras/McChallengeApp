package com.example.mcchallengeapp.database

import com.example.mcchallengeapp.data.entity.MoviesResponse
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val dao: MoviesDao) {

    suspend fun insertFavouriteMovie(moviesResponse: MoviesResponse) =
        dao.insertFavouriteMovie(moviesResponse)

    suspend fun getFavouriteMovies() = dao.getFavouriteMovies()

    suspend fun deleteFavouriteMovie(id: Int) = dao.deleteFavouriteMovie(id)
}