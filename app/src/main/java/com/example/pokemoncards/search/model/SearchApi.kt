package com.example.pokemoncards.search.model

import retrofit2.http.GET

interface SearchApi {

    @GET("v2/cards?pageSize=20")
    suspend fun getCards(): PokemonCardData
}