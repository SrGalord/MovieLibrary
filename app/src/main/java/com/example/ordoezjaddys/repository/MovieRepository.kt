package com.example.ordoezjaddys.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.ordoezjaddys.db.MovieDao
import com.example.ordoezjaddys.db.toEntity
import com.example.ordoezjaddys.db.toMovie
import com.example.ordoezjaddys.model.Movie

class MovieRepository(
    private val movieDao: MovieDao
) {

    fun getMovies(): LiveData<List<Movie>> {
        return movieDao.getAllMovies().map { list ->
            list.map {
                it.toMovie()
            }
        }
    }

    fun getMovie(id: Int): LiveData<Movie?> {
        return movieDao.getMovieById(id).map { entity ->
            entity?.toMovie()
        }
    }

    suspend fun insert(movie: Movie) {
        movieDao.insert(movie.toEntity())
    }

    suspend fun update(movie: Movie) {
        movieDao.update(movie.toEntity())
    }

    suspend fun delete(movie: Movie) {
        movieDao.delete(movie.toEntity())
    }
}