package com.testappssoft.chucknorrisjokestestapp.di

import com.testappssoft.chucknorrisjokestestapp.model.JokeRepository
import com.testappssoft.chucknorrisjokestestapp.viewmodels.SingleRandomJokeFragmentViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun provideSingleRandomJokeFragmentViewModel(jokeRepository: JokeRepository): SingleRandomJokeFragmentViewModel {
        return SingleRandomJokeFragmentViewModel(jokeRepository)
    }
}