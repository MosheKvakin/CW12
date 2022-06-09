package com.example.cw1.LocalDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cw1.data.Abonent

@Database(entities = [Abonent::class],version = 1,exportSchema = false)
//@Database(entities = [Abonent::class],version = 1,exportSchema = false)
abstract class PersonDatabase: RoomDatabase() {

    abstract fun abonentDao() : AbonentDao

    companion object{
        @Volatile
        private var INSTANCE : PersonDatabase? = null

        fun getDataBase(context: Context):PersonDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PersonDatabase::class.java,
                        "DataDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}