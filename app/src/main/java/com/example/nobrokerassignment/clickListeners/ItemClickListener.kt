package com.example.nobrokerassignment.clickListeners

import com.example.nobrokerassignment.room.ItemEntity

interface ItemClickListener {
    fun onItemClicked(itemEntity: ItemEntity)
}