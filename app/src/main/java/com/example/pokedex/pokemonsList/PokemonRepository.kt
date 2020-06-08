package com.example.pokedex.pokemonsList

import com.example.pokedex.network.Api
import com.example.pokedex.pokemon.*

class PokemonRepository {
    private val pokemonService = Api.pokemonService

    suspend fun getPokemonList(): List<PokemonSpecies>? {
        val pokemonResponse = pokemonService.getAll()
        if (pokemonResponse.isSuccessful) {
            return pokemonResponse.body()?.results
        }
        return null
    }
}