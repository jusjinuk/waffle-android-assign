package com.android.example.seminarmanager.ui.user

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.di.NetworkConst.TOKEN_KEY
import com.android.example.seminarmanager.repo.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val toast = MutableLiveData<String>()

    fun login() {
        viewModelScope.launch {
            try {
                val response = repository
                    .login(username.value ?: "", password.value ?: "")
                editor.putString(TOKEN_KEY, response.token)
                editor.commit()
                toast.value = "로그인 성공"
            } catch (e: Exception) {
                toast.value = e.message
            }
        }
    }
}