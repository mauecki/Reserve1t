package com.example.reservapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Company::class, Reservation::class], version = 1, exportSchema = false)
abstract class CompanyDatabase:RoomDatabase() {

    abstract fun companyDao():CompanyDao

    companion object{
        @Volatile
        private var INSTANCE: CompanyDatabase? = null

        fun getDatabase(context: Context): CompanyDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CompanyDatabase::class.java,
                "company_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}