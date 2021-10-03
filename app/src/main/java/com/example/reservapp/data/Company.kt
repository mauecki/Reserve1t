package com.example.reservapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "company_table")
data class Company(
    @PrimaryKey(autoGenerate = true)
  val id: Int,
  val nazwa: String,
  val wlascicel: String,
  val telefon: String,
  val miasto: String,
  val ulica: String,
  val kategoria:String,
  val opis: String,
  val mail:String
): Parcelable