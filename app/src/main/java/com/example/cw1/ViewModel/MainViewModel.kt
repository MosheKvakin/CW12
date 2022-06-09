package com.example.cw1.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cw1.LocalDatabase.PersonDatabase
import com.example.cw1.Repository.AbonentRepository
import com.example.cw1.data.Abonent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {

    val abonentInfo: LiveData<List<Abonent>>

    private val abonentRepository: AbonentRepository

    init {
        val abonentDao = PersonDatabase.getDataBase(application).abonentDao()
        abonentRepository = AbonentRepository(abonentDao)
        abonentInfo = abonentRepository.abonentInfo
    }

    fun addAbonent(info : Abonent){
        viewModelScope.launch(Dispatchers.IO) {
            abonentRepository.addAbonent(info)
        }
    }

    fun updateAbonent(info: Abonent){
        viewModelScope.launch(Dispatchers.IO) {
            abonentRepository.updateAbonent(info)
        }
    }

    fun deleteAbonent(info: Abonent){
        viewModelScope.launch(Dispatchers.IO) {
            abonentRepository.deleteAbonent(info)
        }
    }

    fun deleteAllAbonent(){
        viewModelScope.launch(Dispatchers.IO) {
            abonentRepository.deleteAllAbonent()
        }
    }

    fun searchDatabaseAbonent(searchQuery: String): LiveData<List<Abonent>> {
        return abonentRepository.searchDatabaseAbonent(searchQuery).asLiveData()
    }




}