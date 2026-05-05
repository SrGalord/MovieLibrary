package com.example.ordoezjaddys.db

import com.example.ordoezjaddys.model.Movie

fun MovieEntity.toMovie(): Movie {

    return Movie(
        id,
        title,
        year,
        genre,
        rating,
        watched
    )
}

fun Movie.toEntity(): MovieEntity {

    return MovieEntity(
        id,
        title,
        year,
        genre,
        rating,
        watched
    )
}