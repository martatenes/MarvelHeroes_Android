package com.openbank.marvelheroes.model

import android.os.Parcelable

data class CharacterData(val offset: Int, val limit: Int, val total: Int, val count: Int,
                         var results: MutableList<Character>)