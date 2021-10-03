package com.example.reservapp.data

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Company>>
    val readAllReservation: LiveData<List<Reservation>>

    private val repository: CompanyRepository
    init{
        val companyDao=CompanyDatabase.getDatabase(application).companyDao()
        repository = CompanyRepository(companyDao)
        readAllData = repository.readAllData
        readAllReservation =repository.readAllReservation
    }

    fun yourCompany(mail: String): LiveData<List<Company>>{
    return repository.yourCompany(mail)
    }

    fun searchCompany(search: String): LiveData<List<Company>>{
        return repository.searchCompany(search)
    }

    fun searchById(search: Int):Company{
        return repository.searchById(search)
    }

    fun addCompany(company: Company){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCompany(company)
        }
    }

    fun deleteCompany(company: Company){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCompany(company)
        }
    }

    fun deleteAllCompany(search: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllCompany(search)
        }
    }

    fun updateCompany(company: Company){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCompany(company)
        }
    }

    ////////////////////////////////////////////////////////////////////

    fun addReservation(reservation: Reservation){
        viewModelScope.launch(Dispatchers.IO){
            repository.addReservation(reservation)
        }
    }

    fun readCompanyReservation(idFirmy: Int): LiveData<List<Reservation>>{
        return repository.readCompanyReservation(idFirmy)
    }

    fun readCompanyToAccept(idFirmy: Int): LiveData<List<Reservation>> {
        return repository.readCompanyToAccept(idFirmy)
    }

        fun readYourReservation(mail: String): LiveData<List<Reservation>>{
        return repository.readYourReservation(mail)
    }

    fun deleteWithCompany(mailFirmy: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteWithCompany(mailFirmy)
        }
    }

    fun deleteWithId(idFirmy: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteWithId(idFirmy)
        }
    }

    fun deleteReservation(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteReservation(id)
        }
    }

    fun updateReservation(reservation: Reservation){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateReservation(reservation)
        }
    }

}

