package com.android.example.seminarmanager.di

import com.android.example.seminarmanager.ui.user.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
}