package com.openbank.marvelheroes.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openbank.marvelheroes.R
import com.openbank.marvelheroes.model.Character
import com.openbank.marvelheroes.model.CharacterObservable
import com.openbank.marvelheroes.adapter.CharactersAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers


class MainViewModel: ViewModel(){

    private var marvelObservable: CharacterObservable = CharacterObservable()
    private var charactersAdapter: CharactersAdapter? = null
    private var selected: MutableLiveData<Character>? = MutableLiveData()
    val isLoading = ObservableBoolean()

    fun onRefresh() {
        isLoading.set(true)
        callCharacters()
    }

    fun callCharacters(){
        isLoading.set(true)
        marvelObservable.callCharacters()
    }



    fun getCharacters(): MutableLiveData<List<Character>>{
        isLoading.set(false)
        return marvelObservable.getCharacters()
    }

    fun setCharactersInRecyclerAdapter(characters: List<Character>){
        isLoading.set(false)
        charactersAdapter?.setCharacterList(characters)
        charactersAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerCharactersAdapter(): CharactersAdapter?{
        charactersAdapter = CharactersAdapter(this, R.layout.character_item)
        return charactersAdapter
    }

    fun getSelected(): MutableLiveData<Character>? {
        return selected
    }


    fun onItemClick(index: Int) {
        selected?.value = getCharacterAt(index)
    }

    fun getCharacterAt(position: Int): Character?{
        val characters: List<Character>? = marvelObservable.getCharacters().value
        return characters?.get(position)
    }

    fun getThumbnailAt(position: Int): String{
        val character = getCharacterAt(position)
        return character?.thumbnail?.path + "." + character?.thumbnail?.extension
    }

}

@BindingAdapter("imageUrl")
fun getImageCharacterAt(imgDetail: ImageView, imageUrl: String?){
    imageUrl?.let{
        if (it.isNotEmpty()){
            Picasso.get().load(it).resize(520, 520).centerCrop().into(imgDetail)
        }
    }
}