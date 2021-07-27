package com.masoudk.repository.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.masoudk.datasource.local.model.DBMessage
import com.masoudk.repository.model.Message
import com.masoudk.utils.ResultWrapper

interface LocalDataSource {

    suspend fun saveMessage(message: Message) : Boolean

    suspend fun saveMessage(messages: List<Message>) : Boolean

    suspend fun setMessageStatus(id: String, read: Boolean) : Boolean

    suspend fun updateMessage(message: Message) : Message

    suspend fun getInbox(page: Int) : LiveData<List<Message>>

    fun getInboxPagedSource() : PagingSource<Int, DBMessage>

    suspend fun getTrash(page: Int) : LiveData<List<Message>>

    suspend fun getMessageById(id: String) : LiveData<Message>?

    suspend fun deleteMessage(id: String) : Boolean

    suspend fun moveMessageToTrash(id: String) : Boolean

}