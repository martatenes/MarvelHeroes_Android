package com.openbank.marvelheroes.repository

import androidx.lifecycle.MutableLiveData
import com.openbank.marvelheroes.model.Character
import com.openbank.marvelheroes.model.Comic
import com.openbank.marvelheroes.utils.Resource
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody

interface MarvelRepository {
    fun getCharacters(): MutableLiveData<List<Character>>
    fun callCharactersAPI()
    fun callComicsAPI(id: Long)
    fun getComics(): MutableLiveData<List<Comic>>
}