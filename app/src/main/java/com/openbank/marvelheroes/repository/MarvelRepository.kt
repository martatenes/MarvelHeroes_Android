package com.openbank.marvelheroes.repository

import androidx.lifecycle.MutableLiveData
import com.openbank.marvelheroes.model.Character
import com.openbank.marvelheroes.model.Comic

interface MarvelRepository {

    fun getCharacters(): MutableLiveData<List<Character>>
    fun callCharactersAPI()
    fun callComicsAPI(id: Long)
    fun getComics(): MutableLiveData<List<Comic>>
}