package com.masoudk.datasource.network

import com.masoudk.datasource.network.model.mapToDomain
import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.repository.model.Message
import com.masoudk.utils.ResultWrapper

class RemoteDataSourceImpl constructor(private val gateway: EmailEndpoint) : RemoteDataSource {

    override suspend fun getMessages(id: String): ResultWrapper<List<Message>> {
        val data =  safeApiCall {gateway.getMessages(id).mapToDomain()}

        return data
    }
}