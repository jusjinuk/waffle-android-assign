package com.android.example.seminarmanager.repo

import com.android.example.seminarmanager.network.dto.LoginDto
import com.android.example.seminarmanager.network.dto.PostUserDto
import com.android.example.seminarmanager.network.dto.PutUserDto
import com.android.example.seminarmanager.network.dto.UserDto
import com.android.example.seminarmanager.network.service.UserService
import com.android.example.seminarmanager.repo.Role.INSTRUCTOR
import com.android.example.seminarmanager.repo.Role.IS_PARTICIPANT
import com.android.example.seminarmanager.repo.Role.PARTICIPANT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Role {
    const val IS_PARTICIPANT = true
    const val PARTICIPANT = "participant"
    const val INSTRUCTOR = "instructor"
}

class UserRepository(private val service: UserService) {
    suspend fun login(username: String, password: String): UserDto {
        return withContext(Dispatchers.IO) {
            service.login(LoginDto(username, password))
        }
    }

    suspend fun postUser(
        username: String,
        email: String,
        password: String,
        first_name: String,
        last_name: String,
        role: Boolean,
        university: String,
        accepted: Boolean,
        company: String,
        year: String
    ): UserDto {
        return withContext(Dispatchers.IO) {
            service.postUser(
                PostUserDto(
                    username,
                    email,
                    password,
                    if (first_name.isBlank()) null else first_name,
                    if (last_name.isBlank()) null else last_name,
                    if (role == IS_PARTICIPANT) PARTICIPANT else INSTRUCTOR,
                    if (university.isBlank()) null else university,
                    accepted,
                    if (company.isBlank()) null else company,
                    if (year.isBlank()) null else year.toIntOrNull()
                )
            )
        }
    }

    suspend fun getUserMe(): UserDto {
        return withContext(Dispatchers.IO) {
            service.getUserMe()
        }
    }

    suspend fun putUserMe(
        username: String,
        first_name: String,
        last_name: String
    ): UserDto {
        return withContext(Dispatchers.IO) {
            service.putUserMe(
                PutUserDto(
                    username,
                    if (first_name.isBlank()) null else first_name,
                    if (last_name.isBlank()) null else last_name
                )
            )
        }
    }
}