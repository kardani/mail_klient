package com.masoudk.repository

import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.datasource.network.UsersResponse
import com.masoudk.domain.MessageRepository

class MessageRepositoryImpl constructor(private val remoteDataSource: RemoteDataSource) : MessageRepository{

    override suspend fun getNames() : UsersResponse {
        return remoteDataSource.getNames()
    }

}