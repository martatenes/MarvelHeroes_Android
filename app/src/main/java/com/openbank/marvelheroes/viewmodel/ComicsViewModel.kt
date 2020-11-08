package com.openbank.marvelheroes.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openbank.marvelheroes.R
import com.openbank.marvelheroes.adapter.ComicsAdapter
import com.openbank.marvelheroes.model.Character
import com.openbank.marvelheroes.model.Comic
import com.openbank.marvelheroes.model.ComicObservable

class ComicsViewModel: ViewModel() {

    private var marvelObservable: ComicObservable = ComicObservable()
    private var comicsAdapter: ComicsAdapter? = null
    val isLoading = ObservableBoolean()




    fun callComics(characterId: Long){
        isLoading.set(true)
        marvelObservable.callComics(characterId)
    }

    fun getComics(): MutableLiveData<List<Comic>> {
        isLoading.set(false)
        return marvelObservable.getComics()
    }


    fun getComicAt(position: Int): Comic?{
        val comics: List<Comic>? = marvelObservable.getComics().value
        return comics?.get(position)
    }

    fun setComicsInRecyclerAdapter(comics: List<Comic>){
        isLoading.set(false)
        comicsAdapter?.setComicList(comics)
        comicsAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerComicsAdapter(): ComicsAdapter?{
        comicsAdapter = ComicsAdapter(this, R.layout.comic_item)
        return comicsAdapter
    }




}