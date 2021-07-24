package com.example.nobrokerassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nobrokerassignment.R
import com.example.nobrokerassignment.databinding.ActivityItemDetailScreenBinding
import com.example.nobrokerassignment.databinding.ActivityItemListScreenBinding
import com.example.nobrokerassignment.room.ItemEntity

class ItemDetailScreenActivity : AppCompatActivity() {

    private lateinit var itemEntity: ItemEntity

    private lateinit var binding: ActivityItemDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail_screen)

        supportActionBar?.title = "Item Detail Screen"
        /**
         * getting the data through intent and setting the data.
         */
        binding = ActivityItemDetailScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (intent != null && intent.extras != null) {
            itemEntity = intent.getSerializableExtra("items") as ItemEntity
            binding.tvTitleTitle.text = itemEntity.title
            binding.tvDescDetail.text = itemEntity.subTitle
            Glide.with(this).load(itemEntity.image).into(binding.ivDetailImage)
        }
    }
}