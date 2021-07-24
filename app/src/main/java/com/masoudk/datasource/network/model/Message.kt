package com.masoudk.datasource.network.model

import com.masoudk.repository.model.Message as DomainMessage

data class Message(
    val id: String,
    val date: String,
    val from: String,
    val email: String?,
    val subject: String,
    val content: String,
    val isRead: Boolean,
    val isDelete: Boolean
    )

fun Message.mapToDomain() : DomainMessage {
    return DomainMessage(
        id = this.id,
        date = this.date,
        from = this.from,
        email = this.email ?: "",
        subject = this.subject,
        content = this.content,
        isRead = this.isRead,
        isDelete = this.isDelete
     )
}

fun List<Message>.mapToDomain() : List<DomainMessage>  = this.map { it.mapToDomain() }