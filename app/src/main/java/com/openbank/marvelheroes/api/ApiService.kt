package com.openbank.marvelheroes.api

import com.openbank.marvelheroes.model.CharacterResponse
import com.openbank.marvelheroes.model.ComicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("characters")
    fun getCharacters(@Query("ts") ts: String,
                      @Query("apikey") apiKey: String,
                      @Query("hash") hash: String,
                      @Query("limit") limit: Int): Call<CharacterResponse>

    @GET("characters/{characterId}/comics")
    fun getComics(@Path("characterId") id: Long, @Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String): Call<ComicResponse>
}