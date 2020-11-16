package com.android.example.seminarmanager.network.dto

data class UserDto(
    val username: String,
    val first_name: String,
    val last_name: String,
    val token: String,
    val participant: Participant?,
    val instructor: Instructor?
)

data class Participant(
    val id: Int
)

data class Instructor(
    val id: Int
)

//for PUT /user/login/
data class LoginDto(
    val username: String,
    val password: String
)

//for PUT /user/login/me/
data class PutUserDto(
    val username: String,
    val first_name: String?,
    val last_name: String?
)

//for POST /user/
data class PostUserDto(
    val username: String,
    val email: String,
    val password: String,
    val first_name: String?,
    val last_name: String?,
    val role: String,
    val university: String?,
    val accepted: Boolean?,
    val company: String?,
    val year: Int?
)