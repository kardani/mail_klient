package com.masoudk.repository

import androidx.lifecycle.LiveData
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
        val response = remoteDataSource.getMessages(page)
        if (response is ResultWrapper.Success){
            localDataSource.saveMessage(response.value)
        }

        return response
    }

    override suspend fun getMessagesLive(): LiveData<List<Message>> {
        return localDataSource.getInbox(1)
    }

    override suspend fun setMessageStatus(id: String, read: Boolean): Boolean {
        return localDataSource.setMessageStatus(id, read)
    }

    override suspend fun moveMessageToTrash(id: String): Boolean {
        return localDataSource.moveMessageToTrash(id)
    }

}