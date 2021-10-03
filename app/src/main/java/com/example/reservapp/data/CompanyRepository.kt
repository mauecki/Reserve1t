package com.example.reservapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Executors

class CompanyRepository(private val companyDao: CompanyDao) {
    val readAllData: LiveData<List<Company>> = companyDao.readAllData()


    fun yourCompany(mail: String): LiveData<List<Company>>{
        return companyDao.readYourData(mail)
    }

    fun searchCompany(mail: String): LiveData<List<Company>>{
        return companyDao.searchCompany(mail)
    }

    fun searchById(search: Int):Company{
        return companyDao.searchById(search)
    }

    suspend fun deleteAllCompany(mail: String){
        companyDao.deleteAllCompany(mail)
    }

    suspend fun deleteCompany(company: Company){
        companyDao.deleteCompany(company)
    }

    suspend fun addCompany(company: Company){
        companyDao.addCompany(company)
    }

    suspend fun updateCompany(company: Company){
        companyDao.updateCompany(company)
    }


    ///////////////////////////////////////
    suspend fun addReservation(reservation: Reservation){
        companyDao.addReservation(reservation)
    }

    fun readCompanyReservation(idFirmy: Int): LiveData<List<Reservation>>{
        return companyDao.readCompanyReservation(idFirmy)
    }

    fun readCompanyToAccept(idFirmy: Int): LiveData<List<Reservation>>{
        return companyDao.readCompanyToAccept(idFirmy)
    }


    fun readYourReservation(mail: String): LiveData<List<Reservation>>{
        return companyDao.readYourReservation(mail)
    }

    val readAllReservation: LiveData<List<Reservation>> = companyDao.readAllReservation()

    suspend fun deleteWithCompany(mailFirmy: String){
        companyDao.deleteWithCompany(mailFirmy)
    }

    suspend fun deleteWithId(idFirmy: Int){
        companyDao.deleteWithID(idFirmy)
    }

    suspend fun deleteReservation(id: Int){
        companyDao.deleteReservation(id)
    }

    suspend fun updateReservation(reservation: Reservation){
        companyDao.updateReservation(reservation)
    }

}