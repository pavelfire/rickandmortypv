package com.vk.directop.rickandmortypv.di

import android.content.Context
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import com.vk.directop.rickandmortypv.presentation.CharactersViewModel
import dagger.Module

@Module
class AppModule(val context: Context) {

    fun provideContext(): Context{
        return context
    }

    fun provideCharactersViewModelFactory(
        getCharactersUseCase: GetCharactersUseCase,
    ): CharactersViewModel.CharactersViewModelFactory {
        return CharactersViewModel.CharactersViewModelFactory(getCharactersUseCase = getCharactersUseCase)
    }
}