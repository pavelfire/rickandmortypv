package com.vk.directop.rickandmortypv.di

import com.vk.directop.rickandmortypv.MainActivity
import dagger.Component

@Component (modules = [DataModule::class])
interface AppComponent {

    companion object {

        fun init(): AppComponent {
            return DaggerAppComponent.create()
        }
    }

    fun inject(activity: MainActivity)
}