package com.android.example.seminarmanager

import android.app.Application
import com.android.example.seminarmanager.di.RepositoryModule
import com.android.example.seminarmanager.di.networkModule
import com.android.example.seminarmanager.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SeminarManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SeminarManagerApplication)
            modules(networkModule, RepositoryModule, viewModelModule)
        }
    }
}