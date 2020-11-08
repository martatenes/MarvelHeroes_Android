package com.openbank.marvelheroes.model


data class CharacterResponse(val code: Int, val status: String, val copyright: String,
                             val attributionText: String, val attributionHTML: String,
                             val etag: String, val data: CharacterData
)