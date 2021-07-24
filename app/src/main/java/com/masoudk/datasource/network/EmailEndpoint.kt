package com.masoudk.datasource.network

import com.masoudk.datasource.network.model.Message
import retrofit2.http.GET
import retrofit2.http.Path


interface EmailEndpoint {

    @GET("messages/{page}")
    suspend fun getMessages(@Path("page") page: Int) : List<Message>

}