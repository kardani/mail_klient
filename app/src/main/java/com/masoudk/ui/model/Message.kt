package com.masoudk.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Message(
    val id: Int,
    val date: Date,
    val from: String,
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