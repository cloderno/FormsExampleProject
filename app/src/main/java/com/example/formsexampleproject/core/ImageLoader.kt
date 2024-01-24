package com.example.formsexampleproject.core

import android.widget.ImageView
import com.squareup.picasso.Picasso

interface ImageLoader {
    fun load(url: String, imageView: ImageView)

    // picasso implementation
     class Base(): ImageLoader {
        override fun load(url: String, imageView: ImageView) {
            Picasso.get().load(url).centerCrop()
        }
    }
}