package com.example.cw1.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "tAbonent")
data class Abonent(
    @PrimaryKey(autoGenerate = true)
    val Id : Int,
    val dfName : String,
    val dfInn : String,
    val dfFlagLive : Int
): Parcelable


