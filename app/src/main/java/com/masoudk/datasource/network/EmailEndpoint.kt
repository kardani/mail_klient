package com.masoudk.datasource.network

import com.masoudk.datasource.network.model.Message
import retrofit2.http.GET
import retrofit2.http.Path


interface EmailEndpoint {

    @GET("messages/{id}")
    suspend fun getMessages(@Path("id") id: String) : List<Message>

}