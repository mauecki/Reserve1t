package com.example.reservapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "reservation_table")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val idReserv: Int,
    val nazwaFirmy:String,
    val idFirmy: Int,
    val mailUser:String,
    val data:String,
    val godzina:String,
    val dodatkowe: String,
    val mailOwner: String,
    val check: Int,
    val miasto: String,
    val ulica: String,
    val telefon: String,
    val kategoria: String
): Parcelable