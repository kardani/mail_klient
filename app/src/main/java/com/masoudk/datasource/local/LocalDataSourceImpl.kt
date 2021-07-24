package com.masoudk.datasource.local

import androidx.lifecycle.LiveData
import com.masoudk.repository.datasource.LocalDataSource
import com.masoudk.repository.model.Message

class LocalDataSourceImpl(private val messagesDao: MessagesDao) : LocalDataSource {

    override suspend fun saveMessage(message: Message): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveMessage(messages: List<Message>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateMessage(message: Message): Message {
        TODO("Not yet implemented")
    }

    override suspend fun getInbox(page: Int): LiveData<List<Message>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrash(page: Int): LiveData<List<Message>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessageById(id: String): LiveData<Message> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMessage(id: String): Boolean {
        TODO("Not yet implemented")
    }
}