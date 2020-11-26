package com.android.example.seminarmanager.network.dto

data class UserDto(
    val username: String,
    val first_name: String,
    val last_name: String,
    val token: String,
    val participant: ParticipantProfile?,
    val instructor: InstructorProfile?
)

data class ParticipantProfile(
    val id: Int,
    val seminars: List<SeminarEnrolled>
)

data class InstructorProfile(
    val id: Int,
    val charge: SeminarEnrolled?
)

data class SeminarEnrolled(
    val id: Int,
    val name: String
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