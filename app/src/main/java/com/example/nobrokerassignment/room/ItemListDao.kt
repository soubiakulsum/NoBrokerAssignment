package com.example.nobrokerassignment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ItemListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(itemListEntity: ItemEntity)

    @Query("SELECT * FROM ItemListTable")
    fun getItemList(): LiveData<List<ItemEntity>>


}