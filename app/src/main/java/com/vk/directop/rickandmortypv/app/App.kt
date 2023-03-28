package com.vk.directop.rickandmortypv.app

import android.app.Application
import com.vk.directop.rickandmortypv.di.AppComponent
import com.vk.directop.rickandmortypv.di.AppModule
import com.vk.directop.rickandmortypv.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .build()
    }
}




