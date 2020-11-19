package com.android.example.seminarmanager.ui.main.seminar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.network.dto.SimpleSeminarDto
import com.android.example.seminarmanager.network.dto.UserDto
import com.android.example.seminarmanager.repo.SeminarRepository
import com.android.example.seminarmanager.repo.UserRepository
import com.android.example.seminarmanager.ui.Event
import com.android.example.seminarmanager.ui.SingleEvent
import kotlinx.coroutines.launch
import java.lang.Exception

class SeminarViewModel(
    private val seminarRepository: SeminarRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    val listSeminars = MutableLiveData<List<SimpleSeminarDto>>()
    val user = MutableLiveData<UserDto>()

    fun getSeminarList() {
        viewModelScope.launch {
            try {
                user.value = userRepository.getUserMe()
                listSeminars.value = seminarRepository.getSeminar()
            } catch (e : Exception){
                e.message?.let { SingleEvent.triggerToast.value = Event(it) }
            }
        }
    }
}