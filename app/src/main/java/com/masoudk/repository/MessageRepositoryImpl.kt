package com.masoudk.repository

import androidx.paging.*
import com.masoudk.datasource.local.model.mapToDomain
import com.masoudk.domain.MessageRepository
import com.masoudk.repository.datasource.LocalDataSource
import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.repository.model.Message
import com.masoudk.repository.paging.MessagesRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessageRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : MessageRepository{
    override suspend fun saveMessage(message: Message) {
        localDataSource.saveMessage(message)
    }

    override suspend fun setMessageStatus(id: String, read: Boolean): Boolean {
        return localDataSource.setMessageStatus(id, read)
    }

    override suspend fun moveMessageToTrash(id: String): Boolean {
        return localDataSource.moveMessageToTrash(id)
    }

    override suspend fun restoreMessageToInbox(id: String): Boolean {
        return localDataSource.restoreMessageToInbox(id)
    }

    override suspend fun deleteMessageCompletely(id: String): Boolean {
        return localDataSource.deleteMessage(id)
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


    @ExperimentalPagingApi
    override fun getTrash(config: PagingConfig): Flow<PagingData<Message>> {
        return Pager(
            config = config
        ){
            localDataSource.getTrashPagedSource()
        }.flow.map { it.map { dbMessage -> dbMessage.mapToDomain() } }

    }

}