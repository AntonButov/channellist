package com.butovanton.channellist

import android.app.Application
import com.butovanton.channellist.di.presentationModule
import com.butovanton.channellist.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, presentationModule))
        }
    }
}