package com.example.nobrokerassignment.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ItemListTable")
class ItemEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "subTitle") var subTitle: String?,
    @ColumnInfo(name = "image") var image: String?


) : Serializable {


}