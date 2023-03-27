package com.vk.directop.rickandmortypv.app

import android.app.Application
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.episodes.EpisodesRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryRM
import com.vk.directop.rickandmortypv.di.AppComponent
import com.vk.directop.rickandmortypv.di.AppModule
import com.vk.directop.rickandmortypv.di.DaggerAppComponent
import com.vk.directop.rickandmortypv.di.ServiceLocator
import com.vk.directop.rickandmortypv.domain.usecases.*

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .build()
    }

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

    //---Rx-----------------
    private val locationsRepositoryRx: LocationsRepositoryImpl
        get() = ServiceLocator.provideLocationsRepositoryRx(this)

    val getLocationsRxUseCase: GetLocationsRxUseCase
        get() = GetLocationsRxUseCase(locationsRepositoryRx)
    //----------------------

    private val locationsRepositoryRM: LocationsRepositoryRM
        get() = ServiceLocator.provideLocationsRepositoryRM(this)


    val getLocationsFlowUseCase: GetLocationsFlowUseCase
        get() = GetLocationsFlowUseCase(locationsRepositoryRM)

}




