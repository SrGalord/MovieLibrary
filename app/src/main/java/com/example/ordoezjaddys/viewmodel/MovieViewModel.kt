package com.example.ordoezjaddys.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ordoezjaddys.db.AppDatabase
import com.example.ordoezjaddys.model.Movie
import com.example.ordoezjaddys.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: MovieRepository

    val movies: LiveData<List<Movie>>

    init {
        val dao = AppDatabase
            .getDatabase(application)
            .movieDao()

        repository = MovieRepository(dao)
        movies = repository.getMovies()
    }

    fun getMovie(id: Int): LiveData<Movie?> {
        return repository.getMovie(id)
    }

    fun insert(movie: Movie) {
        viewModelScope.launch {
            repository.insert(movie)
        }
    }

    fun update(movie: Movie) {
        viewModelScope.launch {
            repository.update(movie)
        }
    }

    fun delete(movie: Movie) {
        viewModelScope.launch {
            repository.delete(movie)
        }
    }
}