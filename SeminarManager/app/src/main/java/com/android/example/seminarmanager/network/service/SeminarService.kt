package com.android.example.seminarmanager.network.service

import com.android.example.seminarmanager.network.dto.*
import retrofit2.http.*

interface SeminarService {
    @GET("api/v1/seminar/")
    suspend fun getSeminar(): List<SimpleSeminarDto>

    @POST("api/v1/seminar/")
    suspend fun postSeminar(@Body postSeminarDto: PostSeminarDto): DetailSeminarDto

    @GET("api/v1/seminar/{id}")
    suspend fun getSeminarId(@Path("id") id: Int): DetailSeminarDto

    @FormUrlEncoded
    @POST("api/v1/seminar/{id}/user/")
    suspend fun postSeminarIdUser(
        @Path("id") id: Int,
        @Field("role") role: String
    ): DetailSeminarDto
}