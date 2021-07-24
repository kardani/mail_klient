package com.masoudk.datasource.network.model

import com.masoudk.repository.model.Message as DomainMessage

data class Message(
    val id: String,
    val date: String,
    val from: String,
    val email: String?,
    val subject: String,
    val content: String
    )

fun Message.mapToDomain() : DomainMessage {
    return DomainMessage(
        id = this.id,
        date = this.date,
        from = this.from,
        email = this.email ?: "",
        subject = this.subject,
        content = this.content
     )
}

fun List<Message>.mapToDomain() : List<DomainMessage>  = this.map { it.mapToDomain() }