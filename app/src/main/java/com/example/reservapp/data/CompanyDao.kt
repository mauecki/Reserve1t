package com.example.reservapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCompany(company: Company)

    @Query("SELECT * FROM company_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Company>>

    @Query("SELECT * FROM company_table WHERE mail = :mail ORDER BY id ASC")
    fun readYourData(mail: String): LiveData<List<Company>>

    @Query("SELECT * FROM company_table WHERE id LIKE :search OR nazwa LIKE :search OR wlascicel LIKE :search OR miasto LIKE :search OR kategoria LIKE :search  ORDER BY id ASC")
    fun searchCompany(search: String): LiveData<List<Company>>

    @Query("SELECT * FROM company_table WHERE id = :search")
    fun searchById(search: Int):Company

    @Update
    suspend fun updateCompany(company: Company)

    @Delete
    suspend fun deleteCompany(company: Company)

    @Query("DELETE FROM company_table WHERE mail = :mail")
    suspend fun deleteAllCompany(mail: String)

/////////////////////////////////////////////////////////
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReservation(reservation: Reservation)

    @Query("SELECT * FROM reservation_table ORDER BY data ASC")
    fun readAllReservation(): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservation_table WHERE idFirmy = :idFirmy ORDER BY idReserv ASC")
    fun readCompanyReservation(idFirmy: Int): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservation_table WHERE mailUser = :mail ORDER BY data ASC")
    fun readYourReservation(mail: String): LiveData<List<Reservation>>

    @Query("DELETE FROM reservation_table WHERE mailOwner = :mailOwner")
    suspend fun deleteWithCompany(mailOwner: String)

    @Query("DELETE FROM reservation_table WHERE idFirmy = :idFirmy")
    suspend fun deleteWithID(idFirmy: Int)

    @Query("DELETE FROM reservation_table WHERE idReserv = :id")
    suspend fun deleteReservation(id: Int)

    @Query("SELECT * FROM reservation_table WHERE (idFirmy = :idFirmy) AND (`check`= 0 OR `check`= 1) ORDER BY idReserv ASC")
    fun readCompanyToAccept(idFirmy: Int): LiveData<List<Reservation>>

    @Update
    suspend fun updateReservation(reservation: Reservation)
}