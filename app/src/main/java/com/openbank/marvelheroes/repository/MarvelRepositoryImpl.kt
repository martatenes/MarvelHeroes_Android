package com.openbank.marvelheroes.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.openbank.marvelheroes.api.ApiAdapter
import com.openbank.marvelheroes.model.Character
import com.openbank.marvelheroes.model.CharacterResponse
import com.openbank.marvelheroes.model.Comic
import com.openbank.marvelheroes.model.ComicResponse
import com.openbank.marvelheroes.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MarvelRepositoryImpl(): MarvelRepository {

    val timestamp = Date().time

    val hash = Utils.md5(timestamp.toString() + "b60968f6d13f859459febb4f88ec6e28edfc4e00" + "f4e2c2fffedd8b809f62cd0efb4236ce")

    private var characters = MutableLiveData<List<Character>>()
    private var error = MutableLiveData<Throwable>()
    private var comics = MutableLiveData<List<Comic>>()

    override fun getCharacters(): MutableLiveData<List<Character>>{
        return characters
    }



    override fun callCharactersAPI() {
        val charactersList: ArrayList<Character>? = ArrayList<Character>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCharacters(timestamp.toString(), "f4e2c2fffedd8b809f62cd0efb4236ce", hash, 50)


       call.enqueue( object: Callback<CharacterResponse> {
           override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
                t.stackTrace
            }


            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {

                //TODO: Tratar errores de cliente

                response.body().let {
                    it!!.data.results.forEach { character: Character ->
                        charactersList?.add(character)
                    }
                    characters.value = charactersList

                }

            }

        })
    }



    override fun callComicsAPI(id: Long) {
        val comicsList: ArrayList<Comic>? = ArrayList()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getComics(id, timestamp.toString(), "f4e2c2fffedd8b809f62cd0efb4236ce", hash)


        call.enqueue( object: Callback<ComicResponse> {
            override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
                t.stackTrace
                error.value = t
            }


            override fun onResponse(call: Call<ComicResponse>, response: Response<ComicResponse>) {
                if (response.isSuccessful) {
                    response.body().let {
                        it!!.data.results.forEach { comic: Comic ->
                            comicsList?.add(comic)
                        }
                        comics.value = comicsList

                    }
                }
                else{

                    //TODO: Tratar errores de cliente
                }

            }

        })
    }

    override fun getComics(): MutableLiveData<List<Comic>> {
        return comics
    }
}