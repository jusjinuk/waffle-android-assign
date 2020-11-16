package com.android.example.seminarmanager.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.repo.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val toast = MutableLiveData<String>()

    fun login() {
        viewModelScope.launch {
            try {
                toast.value = repository
                    .login(username.value ?: "", password.value ?: "")
                    .username
            } catch (e: Exception) {
                toast.value = e.message
            }
        }
    }
}