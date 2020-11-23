package com.android.example.seminarmanager.ui.seminar

import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.di.NetworkConst
import com.android.example.seminarmanager.di.NetworkConst.TOKEN_KEY
import com.android.example.seminarmanager.repo.SeminarRepository
import com.android.example.seminarmanager.repo.UserRepository
import com.android.example.seminarmanager.ui.SingleEvent.MAIN_ACTIVITY
import com.android.example.seminarmanager.ui.SingleEvent.navigateToActivity
import com.android.example.seminarmanager.ui.Event
import com.android.example.seminarmanager.ui.SingleEvent.triggerToast
import kotlinx.coroutines.launch

class CreateSeminarViewModel(
    private val repository: SeminarRepository,
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
) : ViewModel() {

    fun login() {
        viewModelScope.launch {
            try {

                triggerToast.value = Event("로그인 성공")
                navigateToActivity.value = Event(MAIN_ACTIVITY)
            } catch (e: Exception) {
                e.message?.let { triggerToast.value = Event(it) }
            }
        }
    }

}