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
    private val repository: SeminarRepository
) : ViewModel() {

    val name = MutableLiveData<String>()
    val capacity = MutableLiveData<String>()
    val count = MutableLiveData<String>()
    val time = MutableLiveData<String>("Choose Time")

    fun createSeminar() {
        viewModelScope.launch {
            try {
                name.value ?: run { error("Name is empty") }
                capacity.value ?: run { error("Capacity is empty") }
                count.value ?: run { error("Count is empty") }
                if (time.value == "Choose Time") error("Time is empty")

                repository.postSeminar(
                    name.value!!,
                    capacity.value!!.toInt(),
                    count.value!!.toInt(),
                    time.value!!,
                    true
                )
                triggerToast.value = Event("세미나 생성 성공")
                navigateToActivity.value = Event(MAIN_ACTIVITY)
            } catch (e: Exception) {
                e.message?.let { triggerToast.value = Event(it) }
            }
        }
    }

}