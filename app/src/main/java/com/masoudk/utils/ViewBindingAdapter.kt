package com.masoudk.utils

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

fun Date.format(format: String = "dd-MM-yyyy") : String {
    val f = SimpleDateFormat(format, Locale.US)
    return f.format(this)
}