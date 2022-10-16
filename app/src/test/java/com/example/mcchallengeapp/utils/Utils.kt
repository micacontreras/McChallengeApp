package com.example.mcchallengeapp.utils

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mcchallengeapp.data.entity.GenericResponse
import com.example.mcchallengeapp.data.entity.MoviesResponse
import com.google.gson.Gson

val LIST_MOVIES_JSON = GenericResponse(listOf())

val movie = MoviesResponse(
    id = 960704,
    adult = false,
    originalLanguage = "ja",
    overview = "El largo y tortuoso viaje de los hermanos Elric llega a su épico final, en el que deben enfrentar una amenaza de otro mundo que afecta a todo el país.",
    originalTitle = "Fullmetal Alchemist: La alquimia final",
    popularity = 3794.105,
    posterPath = "/mQQXEMsukZ2Okd98LCrL33q7tFH.jpg",
    title = "Fullmetal Alchemist: La alquimia final",
    voteAverage = 6.1,
    releaseDate = "2022-06-24",
    voteCount = 63.0
)

fun <T> loadJson(fileName: String, kClass: Class<T>): T =
    Gson().fromJson(
        JsonAssetsUtils.getJsonFromAssets(
            InstrumentationRegistry.getInstrumentation().targetContext,
            fileName
        ),
        kClass
    )