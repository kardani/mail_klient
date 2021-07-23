package com.masoudk.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class
Message(val id: Int,
        val email: String,
        @SerializedName("first_name")
                val firstName: String,
        @SerializedName("last_name")
                val lastName: String,
        val avatar: String): Parcelable{

    fun getFullName() : String{
        return "$firstName $lastName"
    }

}