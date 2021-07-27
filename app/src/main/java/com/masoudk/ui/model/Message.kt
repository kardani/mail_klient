package com.masoudk.ui.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.masoudk.datasource.network.model.mapToDomain
import com.masoudk.utils.format
import com.masoudk.utils.randomString
import com.masoudk.utils.toDate
import com.masoudk.repository.model.Message as DomainMessage
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Message(
    val id: String,
    val date: Date?,
    val from: String,
    val email: String,
    val subject: String,
    val content: String,
    val isRead: Boolean,
    val isDelete: Boolean
    ): Parcelable{

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<Message>()
            {
                override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
                    return oldItem == newItem
                }
            }

        fun dummy() = Message(
            id = UUID.randomUUID().toString(),
            date = Date(),
            from = "From Dummy",
            email = "${randomString(14)}@mk.com",
            subject = "Subject ${randomString(14)}",
            content = "Content\n\n ${randomString(200)}",
            isRead = false,
            isDelete = false
        )


    }



    val shortContent : String
        get() {
            return if(content.length < 100){
                content
            }else{
                content.substring(0..99)
            }
    }

    val shortName : String
        get() {
            return if(from.isEmpty()){
                ""
            }else{
                from.substring(0..0).uppercase(Locale.getDefault())
            }
        }

    val formattedDate : String
        get() {
            return date?.format() ?: "N/A"
        }

}

fun DomainMessage.mapToView() : Message {
    return Message(
        id = this.id,
        date = this.date.toDate(),
        from = this.from,
        email = this.email,
        subject = this.subject,
        content = this.content,
        isRead = this.isRead,
        isDelete = this.isDelete
    )
}

fun List<DomainMessage>.mapToView() : List<Message>  = this.map { it.mapToView() }

fun Message.mapToDomain() : DomainMessage {
    return DomainMessage(
        id = this.id,
        date = this.date?.format("yyyy-MM-dd") ?: "",
        from = this.from,
        email = this.email,
        subject = this.subject,
        content = this.content,
        isRead = this.isRead,
        isDelete = this.isDelete
    )
}