package com.vk.directop.rickandmortypv.di

import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCase{
        return GetCharactersUseCase(charactersRepository)
    }
}