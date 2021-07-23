package com.masoudk.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {

    if(url == null || url == ""){
        return
    }

    Glide.with(this.context)
            .load(url)
            .into(this)
}
