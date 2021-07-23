package com.masoudk.datasource.network

import retrofit2.http.GET
import retrofit2.http.Query


interface UsersEntpoint {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int = 1) : UsersResponse

}