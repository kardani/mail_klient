package com.masoudk.ui.model

import android.os.Parcelable
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
    val content: String
    ): Parcelable{

    val shortContent : String
        get() {
            return if(content.length < 50){
                content
            }else{
                content.substring(0..50)
            }
    }

    val formattedDate : String
        get() {
            return if(content.length < 50){
                content
            }else{
                content.substring(0..50)
            }
        }

}

fun DomainMessage.mapToView() : Message {
    return Message(
        id = this.id,
        date = this.date.toDate(),
        from = this.from,
        email = this.email,
        subject = this.subject,
        content = this.content
    )
}

fun List<DomainMessage>.mapToView() : List<Message>  = this.map { it.mapToView() }