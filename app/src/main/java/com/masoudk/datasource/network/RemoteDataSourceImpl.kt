package com.masoudk.datasource.network

import com.masoudk.repository.datasource.RemoteDataSource

class RemoteDataSourceImpl constructor(private val gateway: UsersEntpoint) : RemoteDataSource {
    override suspend fun getNames() : UsersResponse {
        return gateway.getUsers()
    }
}