package com.vk.directop.rickandmortypv.app

import android.app.Application
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRepositoryImpl
import com.vk.directop.rickandmortypv.di.ServiceLocator
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetSavedCharactersUseCase

class App: Application() {

    private val charactersRepository: CharactersRepositoryImpl
    get() = ServiceLocator.provideCharactersRepository(this)

    val getCharactersUseCase: GetCharactersUseCase
    get() = GetCharactersUseCase(charactersRepository)

    val getSavedCharactersUseCase: GetSavedCharactersUseCase
        get() = GetSavedCharactersUseCase(charactersRepository)

    //val character

    override fun onCreate() {
        super.onCreate()
    }
}




/*
https://medium.com/android-beginners/pagination-with-recyclerview-in-android-3e099c65b6a3


 */