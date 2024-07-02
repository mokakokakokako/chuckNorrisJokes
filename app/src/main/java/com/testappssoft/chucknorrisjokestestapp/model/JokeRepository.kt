package com.testappssoft.chucknorrisjokestestapp.model

import javax.inject.Inject

class JokeRepository @Inject constructor(private val jokeApiService: JokeApiService) {
    suspend fun getJoke(): String {
        return jokeApiService.getJoke()
    }
}
