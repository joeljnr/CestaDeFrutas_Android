package com.jnr.cestadefrutas.utils

import android.content.Context
import androidx.room.*
import com.jnr.cestadefrutas.models.Fruit
import com.jnr.cestadefrutas.models.FruitDao

@Database(entities = arrayOf(Fruit::class), version = 1, exportSchema = false)
abstract class FruitRoomDatabase : RoomDatabase() {

    abstract fun fruitDao(): FruitDao

    companion object {
        @Volatile
        private var INSTANCE: FruitRoomDatabase? = null

        fun getDatabase(context: Context): FruitRoomDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, FruitRoomDatabase::class.java, "fruit_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}