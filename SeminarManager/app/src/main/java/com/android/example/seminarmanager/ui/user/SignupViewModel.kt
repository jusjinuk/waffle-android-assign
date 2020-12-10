package com.android.example.seminarmanager.ui.user

import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.di.NetworkConst
import com.android.example.seminarmanager.di.NetworkConst.TOKEN_KEY
import com.android.example.seminarmanager.repo.Role.INSTRUCTOR
import com.android.example.seminarmanager.repo.Role.PARTICIPANT
import com.android.example.seminarmanager.repo.UserRepository
import com.android.example.seminarmanager.ui.SingleEvent.MAIN_ACTIVITY
import com.android.example.seminarmanager.ui.SingleEvent.navigateToActivity
import com.android.example.seminarmanager.ui.Event
import com.android.example.seminarmanager.ui.SingleEvent.triggerToast
import kotlinx.android.synthetic.main.activity_signup.view.*
import kotlinx.coroutines.launch

class SignupViewModel(
    private val repository: UserRepository,
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val role = MutableLiveData<Int>(R.id.participant_radio)

    fun signup() {
        viewModelScope.launch {
            try {
                username.value ?: run { error("Name is empty") }
                password.value ?: run { error("Capacity is empty") }
                email.value ?: run { error("Count is empty") }
                val role_string = when (role.value) {
                    R.id.participant_radio -> PARTICIPANT
                    R.id.instructor_radio -> INSTRUCTOR
                    else -> error("invalid role")
                }

                val response = repository
                    .postUser(
                        username.value ?: "",
                        email.value ?: "",
                        password.value ?: "",
                        "",
                        "",
                        role_string,
                        "",
                        true,
                        "",
                        ""
                    )
                editor.putString(TOKEN_KEY, response.token)
                editor.commit()
                triggerToast.value = Event("회원가입 성공")
                navigateToActivity.value = Event(MAIN_ACTIVITY)
            } catch (e: Exception) {
                e.message?.let { triggerToast.value = Event(it) }
            }
        }
    }
}