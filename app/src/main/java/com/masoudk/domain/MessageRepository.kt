package com.masoudk.domain

import com.masoudk.datasource.network.UsersResponse

interface MessageRepository {

    suspend fun getNames() : UsersResponse

}