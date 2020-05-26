package com.example.pokedex.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.pokemon.*
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val pokemonRepository = PokemonRepository()
    private val _pokemonInfo = MutableLiveData<PokemonInfo>()
    private val _pokemonList = MutableLiveData<List<PokemonSpecies>>()
    public val pokemonList: LiveData<List<PokemonSpecies>> = _pokemonList

    private fun getMutableList() = _pokemonList.value.orEmpty().toMutableList()

    fun loadPokemons() {
        viewModelScope.launch {
            val newList = pokemonRepository.getPokemonList()
            _pokemonList.value = newList.orEmpty()
        }
    }

   /* fun getPokemon(pokemonInfo: PokemonInfo) {
        viewModelScope.launch {
            val getPokemon = pokemonRepository.getPokemonList() ?: return@launch
            _pokemonInfo.value = getPokemon
        }
    }*/

}