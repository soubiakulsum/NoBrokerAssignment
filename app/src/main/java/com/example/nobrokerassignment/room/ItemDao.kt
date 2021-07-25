package com.example.nobrokerassignment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * make the dao class interface and give dao annotation because we provide
 * the dao interface to the roomdatabase and roomdatabase will implement its methods
 */
@Dao
interface ItemDao {
    /**
     * an annotation generated for the insertion of the data
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(itemListEntity: ItemEntity)

    /**
     * query for the selection of the whole list and returning live data
     */

    @Query("SELECT * FROM ItemListTable")
    fun getItemList(): LiveData<List<ItemEntity>>

    /**
     * query for the search option of title and description
     */

    @Query("SELECT * FROM ItemListTable WHERE title LIKE '%' || :search || '%' OR subTitle LIKE '%' || :search || '%'")
    fun getSearchItem(search : String ) : LiveData<List<ItemEntity>>

}