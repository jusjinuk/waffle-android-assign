package com.android.example.seminarmanager.di

import android.app.Application
import android.content.SharedPreferences
import com.android.example.seminarmanager.BuildConfig
import com.android.example.seminarmanager.di.NetworkConst.BASE_URL
import com.android.example.seminarmanager.di.NetworkConst.PREFS_FILENAME
import com.android.example.seminarmanager.di.NetworkConst.TOKEN_KEY
import com.android.example.seminarmanager.network.service.SeminarService
import com.android.example.seminarmanager.network.service.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConst {
    const val BASE_URL = "https://waffle-backend-jinuk.shop"
    const val PREFS_FILENAME = "prefs"
    const val TOKEN_KEY = "token"
    const val IS_INSTRUCTOR = "is_instructor"
    const val IS_IN_CHARGE = "is_in_charge"
}

val networkModule = module {
    single { provideSharedPreference(androidApplication()) }
    single<SharedPreferences.Editor> { provideSharedPreferenceEditor(get()) }
    single { provideOkHttpClient(get()) }
    single {
        provideRetrofit(
            get(),
            BASE_URL
        )
    }
    single { provideUserService(get()) }
    single { provideSeminarService(get()) }
}

private fun provideSharedPreference(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences(
        PREFS_FILENAME,
        android.content.Context.MODE_PRIVATE
    )
}

private fun provideSharedPreferenceEditor(sharedPreferences: SharedPreferences) =
    sharedPreferences.edit()

private fun provideOkHttpClient(prefs: SharedPreferences): OkHttpClient {
    var okHttpClient = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClient = okHttpClient
            .addInterceptor(loggingInterceptor)
    }

    val authInterceptor = object : Interceptor {
        val AUTHORIZATION = "Authorization"
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = prefs.getString(TOKEN_KEY, null)
            if (token != null) {
                val auth = "Token $token"
                val request = chain.request()
                val builder = request.newBuilder().header(AUTHORIZATION, auth)
                return chain.proceed(builder.build())
            } else {
                return chain.proceed(chain.request())
            }
        }
    }

    return okHttpClient
        .addInterceptor(authInterceptor)
        .build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideUserService(retrofit: Retrofit): UserService =
    retrofit.create(UserService::class.java)

private fun provideSeminarService(retrofit: Retrofit): SeminarService =
    retrofit.create(SeminarService::class.java)