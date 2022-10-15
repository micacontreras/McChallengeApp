package com.example.mcchallengeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mcchallengeapp.data.entity.MoviesResponse

@Database(
    entities = [MoviesResponse::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}