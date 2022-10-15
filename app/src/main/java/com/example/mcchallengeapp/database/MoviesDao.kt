package com.example.mcchallengeapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mcchallengeapp.data.entity.MoviesResponse

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteMovie(moviesResponse: MoviesResponse)

    @Query("SELECT * FROM movie")
    suspend fun getFavouriteMovies(): List<MoviesResponse>

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteFavouriteMovie(id: Int)
}