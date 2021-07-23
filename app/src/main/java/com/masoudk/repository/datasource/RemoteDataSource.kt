package com.masoudk.repository.datasource

import com.masoudk.datasource.network.UsersResponse

interface RemoteDataSource {

    suspend fun getNames() : UsersResponse

}