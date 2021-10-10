package com.example.pokemoncards.search.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemoncards.search.model.PokemonCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    val pokemonCardLiveData = MutableLiveData<List<PokemonCardData.Data>>()
    private lateinit var originalPokemonCardList: List<PokemonCardData.Data>

    init {
        viewModelScope.launch {
            searchRepository.getCards().collect {
                originalPokemonCardList = it.data
                pokemonCardLiveData.postValue(it.data)
            }
        }
    }

    fun filter(query: String) {
        if (query.isEmpty() || query.length < 3) {
            pokemonCardLiveData.postValue(originalPokemonCardList)
            return
        }
        val filteredList = mutableListOf<PokemonCardData.Data>()
        originalPokemonCardList.forEach {
            it.name?.run {
                if (it.name.contains(query,true)) filteredList.add(it)
            }
        }
        pokemonCardLiveData.postValue(filteredList)
    }

    fun sortByHP() {
        viewModelScope.launch {
            val pokemonList = pokemonCardLiveData.value
            pokemonList?.let { list ->
                val sortedPokemonList = list.sortedByDescending { it.hp }
                pokemonCardLiveData.postValue(sortedPokemonList)
            }
        }
    }

    fun sortByLevel() {
        viewModelScope.launch {
            val pokemonList = pokemonCardLiveData.value
            pokemonList?.let { list ->
                val sortedPokemonList = list.sortedByDescending { it.level }
                pokemonCardLiveData.postValue(sortedPokemonList)
            }
        }
    }

}