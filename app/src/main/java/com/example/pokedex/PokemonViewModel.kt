package com.example.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val pokemonInfoRepository: PokemonInfoRepository = PokemonInfoRepository()
    private val _pokemonInfo = MutableLiveData<List<PokemonInfo>>()
    public val pokemonInfo: LiveData<List<PokemonInfo>> = _pokemonInfo
    private val _pokemonList = MutableLiveData<List<PokemonInfo>>()

    private fun getMutableList() = _pokemonList.value.orEmpty().toMutableList()

    fun loadPokemons() {
        viewModelScope.launch {
            val newList = pokemonInfoRepository.getPokemonList()
            _pokemonList.value = getMutableList().apply {
                clear()
                newList?.let { addAll(it) }
            }
        }
    }

    fun getPokemon(pokemonInfo: PokemonInfo) {
        viewModelScope.launch {
            val getPokemon = pokemonInfoRepository.getPokemonList() ?: return@launch
            _pokemonInfo.value = getPokemon
        }
    }

}