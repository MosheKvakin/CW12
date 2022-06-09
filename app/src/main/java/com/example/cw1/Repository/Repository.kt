package com.example.cw1.Repository

import androidx.lifecycle.LiveData
import com.example.cw1.LocalDatabase.AbonentDao
import com.example.cw1.data.Abonent
import kotlinx.coroutines.flow.Flow



class AbonentRepository(private val AbonentDao : AbonentDao) {


    val abonentInfo: LiveData<List<Abonent>> = AbonentDao.getInfo()

    suspend fun addAbonent(info : Abonent){
        AbonentDao.addAbonent(info)
    }

    suspend fun updateAbonent(info : Abonent){
        AbonentDao.updateAbonent(info)
    }

    suspend fun deleteAbonent(info: Abonent){
        AbonentDao.deleteAbonent(info)
    }

    suspend fun deleteAllAbonent(){
        AbonentDao.deleteAllAbonent()
    }

    fun searchDatabaseAbonent(searchQuery: String): Flow<List<Abonent>> {
        return AbonentDao.searchDatabase(searchQuery)
    }

}