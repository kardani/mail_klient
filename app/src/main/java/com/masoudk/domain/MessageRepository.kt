package com.masoudk.domain

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.masoudk.datasource.local.model.DBMessage
import com.masoudk.repository.model.Message
import com.masoudk.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    suspend fun getMessages(page: Int) : ResultWrapper<List<Message>>

    suspend fun getMessagesLive() : LiveData<List<Message>>

    suspend fun setMessageStatus(id: String, read: Boolean) : Boolean

    suspend fun moveMessageToTrash(id: String) : Boolean

    fun getInbox(config: PagingConfig) : Flow<PagingData<DBMessage>>

}