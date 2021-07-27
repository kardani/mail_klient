package com.masoudk.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {

    if(url == null || url == ""){
        return
    }

    Glide.with(this.context)
            .load(url)
            .into(this)
}


fun String.toDate(dateFormat: String = "yyyy-MM-dd"): Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    return parser.parse(this)
}

internal val alphanumeric = ('A'..'Z') + ('a'..'z') + ('0'..'9')

fun randomString(length: Int) : String {
    return buildString {
        repeat(length) { append(alphanumeric.random()) }
    }
}

fun Date.format(format: String = "dd-MM-yyyy") : String {
    val f = SimpleDateFormat(format, Locale.US)
    return f.format(this)
}

@BindingAdapter("invisible")
fun View.invisible(invisible : Boolean){
    if(invisible){
        this.visibility = View.INVISIBLE
    }else{
        this.visibility = View.VISIBLE
    }
}