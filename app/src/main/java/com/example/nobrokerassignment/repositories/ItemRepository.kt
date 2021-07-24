package com.example.nobrokerassignment.repositories

import androidx.lifecycle.LiveData
import com.example.nobrokerassignment.room.ItemDao
import com.example.nobrokerassignment.room.ItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemRepository(
    private val itemListDao: ItemDao
) {

    fun getAllItem(): LiveData<List<ItemEntity>> {
        return itemListDao.getItemList()
    }

    fun addItem(itemListEntity: ItemEntity){
        CoroutineScope(Dispatchers.IO).launch {
            itemListDao.addItem(itemListEntity)
        }
    }

    fun getSearchItem(search : String): LiveData<List<ItemEntity>> {
        return itemListDao.getSearchItem(search)
    }

}