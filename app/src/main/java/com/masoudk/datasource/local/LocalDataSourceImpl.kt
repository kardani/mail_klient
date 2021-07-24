package com.masoudk.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.masoudk.datasource.local.model.mapToDomain
import com.masoudk.datasource.local.model.mapToLocal
import com.masoudk.repository.datasource.LocalDataSource
import com.masoudk.repository.model.Message

class LocalDataSourceImpl(private val messagesDao: MessagesDao) : LocalDataSource {

    override suspend fun saveMessage(message: Message): Boolean {
        return messagesDao.insert(message.mapToLocal()) > 0
    }

    override suspend fun saveMessage(messages: List<Message>): Boolean {
        return messagesDao.insert(messages.mapToLocal()) > 0
    }

    override suspend fun setMessageStatus(id: String, read: Boolean): Boolean {
        val record = messagesDao.getById(id) ?: return false

        messagesDao.update(record.copy(isRead = read))

        return true
    }

    override suspend fun updateMessage(message: Message): Message {
        messagesDao.update(message.mapToLocal())
        return message
    }

    override suspend fun getInbox(page: Int): LiveData<List<Message>> {
        return messagesDao.getAll().map { it.mapToDomain() }
    }

    override suspend fun getTrash(page: Int): LiveData<List<Message>> {
        return messagesDao.getAll().map { it.mapToDomain() }
    }

    override suspend fun getMessageById(id: String): LiveData<Message>? {
        return messagesDao.getByIdLive(id)?.map { it.mapToDomain() }
    }

    override suspend fun deleteMessage(id: String): Boolean {
        val record = messagesDao.getById(id) ?: return false

        messagesDao.update(record.copy(isDelete = true))

        return true
    }
}