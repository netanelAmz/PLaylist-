package com.example.sfirotgmskotlin.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 *  preparation to using room in case of need to save the data
 */

/*
@Database(entities = [],exportSchema = false,version =1)
//@TypeConverters(Converter::class)
abstract class RoomDB : RoomDatabase() {

    abstract fun roomDao() : RoomDao

    companion object{
        // val DATABASE_NAME: String = "RoomDataBase"

        var INSTANCE: RoomDB? = null

        fun getAppDataBase(context: Context): RoomDB? {
            if (INSTANCE == null){
                synchronized(RoomDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RoomDB::class.java, "RoomDataBase")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}*/
