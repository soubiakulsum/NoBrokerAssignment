package com.example.nobrokerassignment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(itemListEntity: ItemEntity)

    @Query("SELECT * FROM ItemListTable")
    fun getItemList(): LiveData<List<ItemEntity>>
//    @Query("SELECT * FROM ItemListTable WHERE title LIKE '%' || :search || '%' OR subTitle LIKE '%' || :search || '%'")

}