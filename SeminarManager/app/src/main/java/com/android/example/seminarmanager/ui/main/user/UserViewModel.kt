package com.android.example.seminarmanager.ui.main.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.network.dto.SeminarEnrolled
import com.android.example.seminarmanager.network.dto.UserDto
import com.android.example.seminarmanager.repo.UserRepository
import com.android.example.seminarmanager.ui.Event
import com.android.example.seminarmanager.ui.SingleEvent
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    val user = MutableLiveData<UserDto>()
    val userName = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()

    fun getUserMe() {
        viewModelScope.launch {
            try {
                user.value = userRepository.getUserMe()
                userName.value = user.value!!.username
                firstName.value = user.value!!.first_name
                lastName.value = user.value!!.last_name
            } catch (e: Exception) {
                e.message?.let { SingleEvent.triggerToast.value = Event(it) }
            }
        }
    }

    fun putUserMe() {
        viewModelScope.launch {
            try {
                userName.value?.let {
                    firstName.value?.let { it1 ->
                        lastName.value?.let { it2 ->
                            user.value = userRepository.putUserMe(it, it1, it2)
                        }
                    }
                }
                SingleEvent.triggerToast.value = Event("수정되었습니다")
            } catch (e: Exception) {
                e.message?.let { SingleEvent.triggerToast.value = Event(it) }
                getUserMe()
            }
        }
    }
}