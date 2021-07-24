package com.masoudk.domain

import com.masoudk.repository.model.Message
import com.masoudk.utils.ResultWrapper

interface MessageRepository {

    suspend fun getMessages(page: Int) : ResultWrapper<List<Message>>

}