package com.example.pokedex.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.pokemon.*
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val pokemonRepository = PokemonRepository()
    private val _pokemonInfo = MutableLiveData<List<PokemonInfo>>()
    public val pokemonInfo: LiveData<List<PokemonInfo>> = _pokemonInfo
    private val _pokemonList = MutableLiveData<List<PokemonSpecies>>()

    private fun getMutableList() = _pokemonList.value.orEmpty().toMutableList()

    fun loadPokemons() {
        viewModelScope.launch {
            val newList = pokemonRepository.getPokemonList()
            _pokemonList.value = getMutableList().apply {
                clear()
                newList?.let { addAll(it) }
            }
        }
    }

   /* fun getPokemon(pokemonInfo: PokemonInfo) {
        viewModelScope.launch {
            val getPokemon = pokemonRepository.getPokemonList() ?: return@launch
            _pokemonInfo.value = getPokemon
        }
    }*/

}