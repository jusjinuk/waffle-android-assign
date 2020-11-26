package com.android.example.seminarmanager.di

import com.android.example.seminarmanager.ui.main.seminar.SeminarViewModel
import com.android.example.seminarmanager.ui.main.user.UserViewModel
import com.android.example.seminarmanager.ui.seminar.CreateSeminarViewModel
import com.android.example.seminarmanager.ui.seminar.DetailSeminarViewModel
import com.android.example.seminarmanager.ui.user.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { SeminarViewModel(get(), get(), get(), get()) }
    viewModel { UserViewModel(get()) }
    viewModel { DetailSeminarViewModel(get()) }
    viewModel { CreateSeminarViewModel(get()) }
}