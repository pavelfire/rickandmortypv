package com.vk.directop.rickandmortypv.di

import com.vk.directop.rickandmortypv.MainActivity
import com.vk.directop.rickandmortypv.presentation.CharactersFragment
import com.vk.directop.rickandmortypv.presentation.EpisodesFragment
import com.vk.directop.rickandmortypv.presentation.LocationsFragment
import dagger.Component

@Component (modules = [DataModule::class, AppModule::class, DomainModule::class])
interface AppComponent {

//    companion object {
//
//        fun init(): AppComponent {
//            return DaggerAppComponent.create()
//        }
//    }

    fun inject(charactersFragment: CharactersFragment)

    fun inject(locationsFragment: LocationsFragment)

    fun inject(episodesFragment: EpisodesFragment)
}