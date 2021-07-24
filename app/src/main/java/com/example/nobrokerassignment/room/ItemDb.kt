package com.example.nobrokerassignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemDb : RoomDatabase() {

    abstract fun getItemListDao(): ItemDao

    companion object {
        private var INSTANCE: ItemDb? = null

        fun getDbContext(context: Context): ItemDb {
            return if (INSTANCE == null) {
                val builder =
                    Room.databaseBuilder(
                        context.applicationContext,
                        ItemDb::class.java,
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