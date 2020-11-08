package com.openbank.marvelheroes.model

data class ComicData(val offset: Int, val limit: Int, val total: Int, val count: Int,
                         var results: MutableList<Comic>)