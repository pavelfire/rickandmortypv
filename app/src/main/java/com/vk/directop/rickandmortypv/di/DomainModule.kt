package com.vk.directop.rickandmortypv.di

import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import dagger.Module

@Module
class DomainModule {

    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCase{
        return GetCharactersUseCase(charactersRepository)
    }
}