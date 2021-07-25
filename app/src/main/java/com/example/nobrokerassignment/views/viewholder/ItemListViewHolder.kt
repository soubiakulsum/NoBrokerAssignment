package com.example.nobrokerassignment.views.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nobrokerassignment.R
import com.example.nobrokerassignment.clickListeners.ItemClickListener
import com.example.nobrokerassignment.room.ItemEntity

class ItemListViewHolder(private val view: View, private val itemClickListener: ItemClickListener) :
    RecyclerView.ViewHolder(view) {

    fun setData(itemEntity: ItemEntity) {

        view.apply {
            val mIvImage = findViewById<ImageView>(R.id.ivItemImage)
            val mTvTitle = findViewById<TextView>(R.id.tvTitle)
            val mTvDesc = findViewById<TextView>(R.id.tvDesc)
            val mCvItems = findViewById<CardView>(R.id.cvItems)

            mTvTitle.text = itemEntity.title
            mTvDesc.text = itemEntity.subTitle
            Glide.with(context).load(itemEntity.image).into(mIvImage)

            mCvItems.setOnClickListener {
                itemClickListener.onItemClicked(itemEntity)

            }

        }
    }

}