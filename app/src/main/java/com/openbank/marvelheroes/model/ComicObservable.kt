package com.openbank.marvelheroes.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.openbank.marvelheroes.repository.MarvelRepository
import com.openbank.marvelheroes.repository.MarvelRepositoryImpl

class ComicObservable: BaseObservable() {

    private var marvelRespository: MarvelRepository = MarvelRepositoryImpl()

    // Repositorio
    fun callComics(characterId: Long){
        marvelRespository.callComicsAPI(characterId)
    }

    // ViewModel
    fun getComics(): MutableLiveData<List<Comic>> {
        return marvelRespository.getComics()
    }
}