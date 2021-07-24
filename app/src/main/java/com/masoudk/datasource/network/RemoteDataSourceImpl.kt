package com.masoudk.datasource.network

import com.masoudk.datasource.network.model.mapToDomain
import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.repository.model.Message
import com.masoudk.utils.ResultWrapper

class RemoteDataSourceImpl constructor(private val gateway: EmailEndpoint) : RemoteDataSource {
    override suspend fun getMessages(page: Int) : ResultWrapper<List<Message>> {
        return safeApiCall {gateway.getMessages(page).mapToDomain()}
    }
}