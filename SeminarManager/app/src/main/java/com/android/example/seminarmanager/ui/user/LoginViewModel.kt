package com.android.example.seminarmanager.ui.user

import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.di.NetworkConst
import com.android.example.seminarmanager.di.NetworkConst.TOKEN_KEY
import com.android.example.seminarmanager.repo.UserRepository
import com.android.example.seminarmanager.ui.SingleEvent.MAIN_ACTIVITY
import com.android.example.seminarmanager.ui.SingleEvent.navigateToActivity
import com.android.example.seminarmanager.ui.Event
import com.android.example.seminarmanager.ui.SingleEvent.triggerToast
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun login() {
        viewModelScope.launch {
            try {
                val response = repository
                    .login(username.value ?: "", password.value ?: "")
                editor.putString(TOKEN_KEY, response.token)
                editor.commit()
                triggerToast.value = Event("로그인 성공")
                navigateToActivity.value = Event(MAIN_ACTIVITY)
            } catch (e: Exception) {
                e.message?.let {  triggerToast.value = Event(it) }
            }
        }
    }

    val checkToken : () -> (Unit) = {
        if(prefs.getString(NetworkConst.TOKEN_KEY, null) != null) {
            triggerToast.value = Event("자동 로그인 됨")
            navigateToActivity.value = Event(MAIN_ACTIVITY)
        }
    }
}