package com.example.ezetap.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ezetap.R

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}

@BindingAdapter("labelText")
fun bindLabelText(textView: TextView, text: String){
    textView.text = String.format(textView.context.getString(R.string.text_label), text.trim())
}