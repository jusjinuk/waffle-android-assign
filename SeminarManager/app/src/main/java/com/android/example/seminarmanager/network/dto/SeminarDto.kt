package com.android.example.seminarmanager.network.dto

data class SimpleSeminarDto(
    val id: Int,
    val name: String,
    val instructors: List<SeminarInstructor>
)

data class DetailSeminarDto(
    val id: Int,
    val name: String,
    val capacity: Int,
    val count: Int,
    val time: String,
    val instructors: List<SeminarInstructor>,
    val participants: List<SeminarParticipant>
)

sealed class SeminarPerson

data class SeminarInstructor(
    val id: Int,
    val username: String
) : SeminarPerson()

data class SeminarParticipant(
    val id: Int,
    val username: String
) : SeminarPerson()

data class PostSeminarDto(
    val name: String,
    val capacity: Int,
    val count: Int,
    val time: String,
    val online: Boolean?
)