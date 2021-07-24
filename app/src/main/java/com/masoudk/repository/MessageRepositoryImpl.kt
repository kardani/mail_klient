package com.masoudk.repository

import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.domain.MessageRepository
import com.masoudk.repository.datasource.LocalDataSource
import com.masoudk.repository.model.Message
import com.masoudk.utils.ResultWrapper

class MessageRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : MessageRepository{

    override suspend fun getMessages(page: Int): ResultWrapper<List<Message>> {
        return remoteDataSource.getMessages(page)
    }

}