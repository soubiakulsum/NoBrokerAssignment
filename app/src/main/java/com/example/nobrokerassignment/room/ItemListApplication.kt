package com.example.nobrokerassignment.room

import android.app.Application
import com.example.nobrokerassignment.repositories.ItemListRepository

class ItemListApplication : Application() {

    private val itemListDao by lazy {
        val database = ItemListDb.getDbContext(this)
        database.getItemListDao()
    }

    val itemListRepo by lazy {
        ItemListRepository(itemListDao)
    }
}