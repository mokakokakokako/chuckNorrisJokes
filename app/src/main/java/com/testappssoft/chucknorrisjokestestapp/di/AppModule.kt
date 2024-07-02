package com.testappssoft.chucknorrisjokestestapp.di

import com.testappssoft.chucknorrisjokestestapp.model.JokeApiService
import com.testappssoft.chucknorrisjokestestapp.model.JokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJokeApiService(): JokeApiService {
        return JokeApiService()
    }

    @Provides
    @Singleton
    fun provideJokeRepository(jokeApiService: JokeApiService): JokeRepository {
        return JokeRepository(jokeApiService)
    }
}
