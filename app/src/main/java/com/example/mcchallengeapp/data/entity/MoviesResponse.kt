package com.example.mcchallengeapp.data.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "Movie")
data class MoviesResponse(
    @PrimaryKey val id: Int,
    @ColumnInfo val adult: Boolean,
    @ColumnInfo @SerializedName("original_language") val originalLanguage: String,
    @ColumnInfo @SerializedName("original_title") val originalTitle: String,
    @ColumnInfo val overview: String,
    @ColumnInfo val popularity: Double,
    @ColumnInfo @SerializedName("poster_path") val posterPath: String,
    @ColumnInfo @SerializedName("release_date") val releaseDate: String,
    @ColumnInfo val title: String,
    @ColumnInfo @SerializedName("vote_average") val voteAverage: Double,
    @ColumnInfo @SerializedName("vote_count") val voteCount: Double
)