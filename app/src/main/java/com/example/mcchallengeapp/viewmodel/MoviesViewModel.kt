package com.example.mcchallengeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcchallengeapp.data.entity.MoviesResponse
import com.example.mcchallengeapp.data.repository.ApiServiceRepository
import com.example.mcchallengeapp.data.source.SharedPreferencesCustom
import com.example.mcchallengeapp.database.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: ApiServiceRepository,
    private val sharedPreferencesCustom: SharedPreferencesCustom,
    private val localRepository: MoviesRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<MoviesResponse>>()
    private val _onError = MutableLiveData<Boolean>()

    val movies: LiveData<List<MoviesResponse>>
        get() = _movies

    fun getTopMovies() {
        viewModelScope.launch {
            try {
                val movies = repository.getMovies(sharedPreferencesCustom.fetchLanguage())
                if (movies.isSuccessful) {
                    val favMovies = localRepository.getFavouriteMovies()
                    val searchedMovies = movies.body()?.results
                    _movies.value = searchedMovies?.filter { moviesResponse ->
                        !favMovies.contains(moviesResponse)
                    }
                } else _onError.value = true

            } catch (e: Exception) {
                Log.e("Api call with error", "getTopMovies fail")
                _onError.value = true
            }
        }
    }

    fun getSearchedMovie(movie: String) {
        viewModelScope.launch {
            try {
                val movies = repository.searchMovie(movie, sharedPreferencesCustom.fetchLanguage())
                if (movies.isSuccessful) {
                    _movies.value = movies.body()?.results
                } else _onError.value = true

            } catch (e: Exception) {
                Log.e("Api call with error", "getSearchedMovie fail")
                _onError.value = true
            }
        }
    }

    fun insertMovieInDB(moviesResponse: MoviesResponse) {
        viewModelScope.launch {
            try {
                localRepository.insertFavouriteMovie(moviesResponse)
            } catch (e: Exception) {
                Log.e("Error in DB", "insert new movie fail")
                _onError.value = true
            }
        }
    }

    fun deleteMovieInDB(movieId: Int) {
        viewModelScope.launch {
            try {
                localRepository.deleteFavouriteMovie(movieId)
            } catch (e: Exception) {
                Log.e("Error in DB", "Delete movie fail")
                _onError.value = true
            }
        }
    }

    fun getFavouriteMovies() {
        viewModelScope.launch {
            try {
                _movies.value = localRepository.getFavouriteMovies()
            } catch (e: Exception) {
                Log.e("Error in DB", "Get all the favourite movies fail")
                _onError.value = true
            }
        }
    }
}