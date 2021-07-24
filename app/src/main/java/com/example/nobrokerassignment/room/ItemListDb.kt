package com.example.nobrokerassignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemListDb : RoomDatabase() {

    abstract fun getItemListDao(): ItemListDao

    companion object {
        private var INSTANCE: ItemListDb? = null

        fun getDbContext(context: Context): ItemListDb {
            return if (INSTANCE == null) {
                val builder =
                    Room.databaseBuilder(
                        context.applicationContext,
                        ItemListDb::class.java,
                        "foodDB"
                    )
                        .fallbackToDestructiveMigration()
                INSTANCE = builder.build()

                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }
}