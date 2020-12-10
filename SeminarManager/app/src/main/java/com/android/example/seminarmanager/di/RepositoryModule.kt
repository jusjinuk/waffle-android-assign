package com.android.example.seminarmanager.di

import com.android.example.seminarmanager.repo.SeminarRepository
import com.android.example.seminarmanager.repo.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single {
        UserRepository(get())
    }
    single {
        SeminarRepository(get())
    }
}