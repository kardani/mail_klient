package com.masoudk.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.masoudk.datasource.network.model.mapToDomain
import com.masoudk.repository.model.Message as DomainMessage

@Entity(tableName = "message")
data class DBMessage(
    @PrimaryKey
    val id: String,
    val date: String,
    val from: String,
    val email: String,
    val subject: String,
    val content: String,
    val isRead: Boolean,
    val isDelete: Boolean
    )

fun DomainMessage.mapToLocal() : DBMessage {
    return DBMessage(
        id = this.id,
        date = this.date,
        from = this.from,
        email = this.email,
        subject = this.subject,
        content = this.content,
        isRead = this.isRead,
        isDelete = this.isDelete
    )
}

fun List<DomainMessage>.mapToLocal() : List<DBMessage>  = this.map { it.mapToLocal() }

fun DBMessage.mapToDomain() : DomainMessage {
    return DomainMessage(
        id = this.id,
        date = this.date,
        from = this.from,
        email = this.email,
        subject = this.subject,
        content = this.content,
        isRead = this.isRead,
        isDelete = this.isDelete
     )
}

fun List<DBMessage>.mapToDomain() : List<DomainMessage>  = this.map { it.mapToDomain() }