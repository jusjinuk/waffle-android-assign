package com.android.example.seminarmanager.repo

import com.android.example.seminarmanager.network.dto.*
import com.android.example.seminarmanager.network.service.SeminarService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SeminarRepository(private val service: SeminarService) {
    suspend fun getSeminar() : List<SimpleSeminarDto> {
        return withContext(Dispatchers.IO) {
            service.getSeminar()
        }
    }

    suspend fun postSeminar(
        name: String,
        capacity: Int,
        count: Int,
        time: String,
        online: Boolean
    ): DetailSeminarDto {
        return withContext(Dispatchers.IO) {
            service.postSeminar(
                PostSeminarDto(
                    name,
                    capacity,
                    count,
                    time,
                    online
                )
            )
        }
    }

    suspend fun getSeminarId(id: Int) {
        return withContext(Dispatchers.IO) {
            service.getSeminarId(id)
        }
    }

    suspend fun enrollSeminar(seminar_id: Int, role: String) {
        return withContext(Dispatchers.IO) {
            service.postSeminarIdUser(seminar_id, role)
        }
    }

}