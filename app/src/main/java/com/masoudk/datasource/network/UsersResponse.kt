package com.masoudk.datasource.network

import com.masoudk.repository.model.Message
import com.google.gson.annotations.SerializedName

data class UsersResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<Message>
    )