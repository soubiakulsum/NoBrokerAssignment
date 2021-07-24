package com.example.nobrokerassignment.repositories

import androidx.lifecycle.LiveData
import com.example.nobrokerassignment.room.ItemListDao
import com.example.nobrokerassignment.room.ItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemListRepository(
    private val itemListDao: ItemListDao
) {

    fun getAllItem(): LiveData<List<ItemEntity>> {
        return itemListDao.getItemList()
    }

    fun addItem(itemListEntity: ItemEntity){
        CoroutineScope(Dispatchers.IO).launch {
            itemListDao.addItem(itemListEntity)
        }
    }
}