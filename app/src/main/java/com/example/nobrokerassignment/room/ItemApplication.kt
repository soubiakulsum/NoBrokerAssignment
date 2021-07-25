package com.example.nobrokerassignment.room

import android.app.Application
import com.example.nobrokerassignment.repositories.ItemRepository

class ItemApplication : Application() {

    private val itemListDao by lazy {
        val database = ItemDb.getDbContext(this)
        database.getItemListDao()
    }

    val itemListRepo by lazy {
        ItemRepository(itemListDao)
    }
}