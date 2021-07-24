package com.masoudk.repository.datasource

import com.masoudk.repository.model.Message
import com.masoudk.utils.ResultWrapper

interface RemoteDataSource {

    suspend fun getMessages(page: Int) : ResultWrapper<List<Message>>

}