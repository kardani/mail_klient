package com.masoudk.domain

import androidx.lifecycle.LiveData
import com.masoudk.repository.model.Message
import com.masoudk.utils.ResultWrapper

interface MessageRepository {

    suspend fun getMessages(page: Int) : ResultWrapper<List<Message>>

    suspend fun getMessagesLive() : LiveData<List<Message>>

    suspend fun setMessageStatus(id: String, read: Boolean) : Boolean

    suspend fun moveMessageToTrash(id: String) : Boolean

}