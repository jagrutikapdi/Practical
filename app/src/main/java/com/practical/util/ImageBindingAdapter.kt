package com.practical.util

import android.os.Handler
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.practical.R


class ImageBindingAdapter {
    companion object {

        private val simpleOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String?) {
            try {
                if (url != null && url != "") {
                    Glide.with(imageView.context)
                        .load(url)
                        .apply(simpleOptions)
                        .into(imageView)
                }
            } catch (e: Exception) {
            }

        }



    }
}
