package com.openbank.marvelheroes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic (val id: Long, val title: String, val description: String): Parcelable