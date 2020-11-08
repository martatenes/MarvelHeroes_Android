package com.openbank.marvelheroes.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.openbank.marvelheroes.repository.MarvelRepository
import com.openbank.marvelheroes.repository.MarvelRepositoryImpl

class CharacterObservable: BaseObservable(){
    private var marvelRespository: MarvelRepository = MarvelRepositoryImpl()

    // Repositorio
    fun callCharacters(){
        marvelRespository.callCharactersAPI()
    }

    // ViewModel
    fun getCharacters(): MutableLiveData<List<Character>> {
        return marvelRespository.getCharacters()
    }


}