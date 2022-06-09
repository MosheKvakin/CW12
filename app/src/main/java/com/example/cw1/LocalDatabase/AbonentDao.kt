package com.example.cw1.LocalDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cw1.data.Abonent
import kotlinx.coroutines.flow.Flow

@Dao
interface AbonentDao {
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAbonent(info : Abonent)
    //: Completable - rx что ли попробывать


    @Update
    fun updateAbonent(info: Abonent)

    @Delete
    fun deleteAbonent(info: Abonent)
    //: Integer
    @Query("DELETE FROM tAbonent")
    fun deleteAllAbonent()

    // @Query("SELECT * FROM tAbonent ORDER BY Id ASC")
    @Query("SELECT * FROM tAbonent")
    fun getInfo(): LiveData<List<Abonent>>

    @Query("SELECT * FROM tAbonent WHERE dfName LIKE :searchQuery ")
    fun searchDatabase(searchQuery:String): Flow<List<Abonent>>

}