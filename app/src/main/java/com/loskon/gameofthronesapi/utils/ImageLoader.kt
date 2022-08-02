package com.loskon.gameofthronesapi.utils

import android.widget.ImageView
import com.loskon.gameofthronesapi.app.glide.GlideApp

object ImageLoader {

    fun load(view: ImageView, url: String) {
        GlideApp.with(view)
            .load(url)
            .circleCrop()
            .into(view)
    }
}