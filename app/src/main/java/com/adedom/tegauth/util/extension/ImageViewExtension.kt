package com.adedom.tegauth.util.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.adedom.tegauth.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.setImageCircle(
    url: String?,
    @DrawableRes placeholder: Int = R.drawable.ic_launcher_foreground
) {
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.placeholderOf(placeholder))
        .circleCrop()
        .into(this)
}
