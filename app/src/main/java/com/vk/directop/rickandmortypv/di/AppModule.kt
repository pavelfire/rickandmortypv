package com.vk.directop.rickandmortypv.di

import android.content.Context
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import com.vk.directop.rickandmortypv.presentation.CharactersViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context{
        return context
    }

}