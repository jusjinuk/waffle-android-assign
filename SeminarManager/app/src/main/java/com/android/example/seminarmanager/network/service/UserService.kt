package com.android.example.seminarmanager.network.service

import com.android.example.seminarmanager.network.dto.LoginDto
import com.android.example.seminarmanager.network.dto.PostUserDto
import com.android.example.seminarmanager.network.dto.PutUserDto
import com.android.example.seminarmanager.network.dto.UserDto
import retrofit2.http.*

interface UserService {
    @PUT("api/v1/user/login/")
    suspend fun login(@Body loginDto: LoginDto): UserDto

    @POST("api/v1/user/")
    suspend fun postUser(@Body postUserDto: PostUserDto): UserDto

    @GET("api/v1/user/me/")
    suspend fun getUserMe(): UserDto

    @PUT("api/v1/user/me/")
    suspend fun putUserMe(@Body putUserDto: PutUserDto): UserDto
}