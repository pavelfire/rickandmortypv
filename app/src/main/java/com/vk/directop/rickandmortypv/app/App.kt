package com.vk.directop.rickandmortypv.app

import android.app.Application
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.episodes.EpisodesRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryImpl
import com.vk.directop.rickandmortypv.di.ServiceLocator
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetEpisodesUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetLocationsUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetSavedCharactersUseCase

class App: Application() {

    private val charactersRepository: CharactersRepositoryImpl
    get() = ServiceLocator.provideCharactersRepository(this)

    val getCharactersUseCase: GetCharactersUseCase
    get() = GetCharactersUseCase(charactersRepository)

    val getSavedCharactersUseCase: GetSavedCharactersUseCase
        get() = GetSavedCharactersUseCase(charactersRepository)

    private val episodesRepository: EpisodesRepositoryImpl
        get() = ServiceLocator.provideEpisodesRepository(this)

    val getEpisodesUseCase: GetEpisodesUseCase
    get() = GetEpisodesUseCase(episodesRepository)

    private val locationsRepository: LocationsRepositoryImpl
        get() = ServiceLocator.provideLocationsRepository(this)

    val getLocationsUseCase: GetLocationsUseCase
        get() = GetLocationsUseCase(locationsRepository)


    override fun onCreate() {
        super.onCreate()
    }
}




