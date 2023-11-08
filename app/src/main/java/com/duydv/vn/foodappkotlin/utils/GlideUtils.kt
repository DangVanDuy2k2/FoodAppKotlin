package com.duydv.vn.foodappkotlin.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.duydv.vn.foodappkotlin.R

object GlideUtils {
    fun loadUrl( url : String,imageView : ImageView){
        if(StringUtils.isEmpty(url)){
            imageView.setImageResource(R.drawable.no_image)
            return
        }

        Glide.with(imageView.context)
            .load(url)
            .error(R.drawable.no_image)
            .into(imageView)
    }
}