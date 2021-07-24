package com.masoudk.datasource.local.model

import com.masoudk.repository.model.Message as DomainMessage

data class DBMessage(
    val id: String,
    val date: String,
    val from: String,
    val email: String,
    val subject: String,
    val content: String
    )

fun DomainMessage.mapToLocal() : DBMessage {
    return DBMessage(
        id = this.id,
        date = this.date,
        from = this.from,
        email = this.email,
        subject = this.subject,
        content = this.content
    )
}

fun DBMessage.mapToDomain() : DomainMessage {
    return DomainMessage(
        id = this.id,
        date = this.date,
        from = this.from,
        email = this.email,
        subject = this.subject,
        content = this.content
     )
}

fun List<DBMessage>.mapToDomain() : List<DomainMessage>  = this.map { it.mapToDomain() }