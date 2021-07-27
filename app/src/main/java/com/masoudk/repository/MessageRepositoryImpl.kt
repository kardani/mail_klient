package com.masoudk.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.masoudk.datasource.local.model.mapToDomain
import com.masoudk.domain.MessageRepository
import com.masoudk.repository.datasource.LocalDataSource
import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.repository.model.Message
import com.masoudk.repository.paging.MessagesRemoteMediator
import com.masoudk.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessageRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : MessageRepository{
    override suspend fun simulateReceiveNewMessage(message: Message) {
        localDataSource.saveMessage(message)
    }

    override suspend fun getMessages(page: Int): ResultWrapper<List<Message>> {
        val response = remoteDataSource.getMessages("")
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

    @ExperimentalPagingApi
    override fun getInbox(config: PagingConfig): Flow<PagingData<Message>> {
        return Pager(
            config = config,
            remoteMediator = MessagesRemoteMediator(localDataSource, remoteDataSource)
            ){
                localDataSource.getInboxPagedSource()
            }.flow.map { it.map { dbMessage -> dbMessage.mapToDomain() } }

    }

}