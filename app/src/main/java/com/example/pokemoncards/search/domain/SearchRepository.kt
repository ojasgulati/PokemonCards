package com.example.pokemoncards.search.domain

import com.example.pokemoncards.search.model.PokemonCardData
import com.example.pokemoncards.search.model.SearchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface SearchRepository {
    fun getCards(): Flow<PokemonCardData>
}

class SearchRepositoryImpl @Inject constructor(
    private val api: SearchApi
) : SearchRepository {

    override fun getCards(): Flow<PokemonCardData> {
        return flow {
            val pokemonCardData = api.getCards()
            emit(pokemonCardData)
        }.flowOn(Dispatchers.IO)
    }
}
