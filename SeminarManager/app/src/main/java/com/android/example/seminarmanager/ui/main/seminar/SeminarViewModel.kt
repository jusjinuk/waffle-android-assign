package com.android.example.seminarmanager.ui.main.seminar

import android.content.SharedPreferences
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.di.NetworkConst
import com.android.example.seminarmanager.network.dto.SimpleSeminarDto
import com.android.example.seminarmanager.network.dto.UserDto
import com.android.example.seminarmanager.repo.SeminarRepository
import com.android.example.seminarmanager.repo.UserRepository
import com.android.example.seminarmanager.ui.Event
import com.android.example.seminarmanager.ui.SingleEvent
import com.android.example.seminarmanager.ui.SingleEvent.triggerMenuRefresh
import kotlinx.coroutines.launch
import java.lang.Exception

class SeminarViewModel(
    private val seminarRepository: SeminarRepository,
    private val userRepository: UserRepository,
    private val prefs: SharedPreferences,
    private val editor: SharedPreferences.Editor
) : ViewModel() {
    val listSeminars = MutableLiveData<List<SimpleSeminarDto>>()
    val user = MutableLiveData<UserDto>()

    fun getSeminarList() {
        viewModelScope.launch {
            try {
                user.value = userRepository.getUserMe()
                listSeminars.value = seminarRepository.getSeminar()
                user.value!!.instructor?.apply {
                    editor.putString(NetworkConst.IS_INSTRUCTOR, "1")
                    editor.commit()
                    triggerMenuRefresh.value = Event(true)
                }
            } catch (e: Exception) {
                e.message?.let { SingleEvent.triggerToast.value = Event(it) }
            }
        }
    }
}