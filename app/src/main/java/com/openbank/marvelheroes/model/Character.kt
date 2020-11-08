package com.openbank.marvelheroes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(val id: Long, val name: String,
                     val description: String,
                     val modified: String,
                     val thumbnail: Thumbnail,
                     val resourceURI: String?,
                     var page : Int,
                    val comics: CollectionItem,
                    val series: CollectionItem,
                     val stories: CollectionItem,
                     val events: CollectionItem): Parcelable
