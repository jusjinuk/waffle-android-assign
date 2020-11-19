package com.android.example.seminarmanager.di

import com.android.example.seminarmanager.ui.main.seminar.SeminarViewModel
import com.android.example.seminarmanager.ui.main.user.UserViewModel
import com.android.example.seminarmanager.ui.user.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { SeminarViewModel(get(), get()) }
    viewModel { UserViewModel(get()) }
}