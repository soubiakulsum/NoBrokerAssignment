package com.example.nobrokerassignment.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * generating an annotation for the entity to create a table for all the columns in the
 * table and generating a respective key.
 */
@Entity(tableName = "ItemListTable")
class ItemEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "subTitle") var subTitle: String?,
    @ColumnInfo(name = "image") var image: String?,


) : Serializable {


}