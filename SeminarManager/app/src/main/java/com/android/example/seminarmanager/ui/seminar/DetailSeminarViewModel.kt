package com.android.example.seminarmanager.ui.seminar

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.seminarmanager.network.dto.DetailSeminarDto
import com.android.example.seminarmanager.repo.SeminarRepository
import com.android.example.seminarmanager.ui.Event
import com.android.example.seminarmanager.ui.SingleEvent.triggerToast
import kotlinx.coroutines.launch

class DetailSeminarViewModel(
    private val repository: SeminarRepository
) : ViewModel() {

    val detailSeminar = MutableLiveData<DetailSeminarDto>()

    fun getSeminarId(seminar_id: Int) {
        viewModelScope.launch {
            try {
                detailSeminar.value = repository.getSeminarId(seminar_id)
            } catch (e: Exception) {
                e.message?.let { triggerToast.value = Event(it) }
            }
        }
    }

    fun enrollSeminar(seminar_id: Int, role: String) {
        viewModelScope.launch {
            try {
                detailSeminar.value = repository.enrollSeminar(seminar_id, role)
                triggerToast.value = Event("등록 성공.")
            } catch (e: Exception) {
                e.message?.let { triggerToast.value = Event(it) }
            }
        }
    }

}