package com.example.nobrokerassignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * an annotation for the database class and declaring the number of entities being used and the version number
 */
@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemDb : RoomDatabase() {

    /**
     * make the db class abstract beacause it is responsible for creating a database
     * and returning the dao object.
     */

    abstract fun getItemListDao(): ItemDao

    /**
     * calling the database function inside companion object and making a single
     * instance of the databse.
     */

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