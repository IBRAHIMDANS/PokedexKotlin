package com.example.pokedex.pokemon

import com.example.pokedex.network.Api
import com.example.pokedex.pokemon.PokemonSpecies

class PokemonRepository {
    private val pokemonService = Api.pokemonService

    suspend fun getPokemonList(): List<PokemonSpecies>? {
        val pokemonResponse = pokemonService.getAll()
        if (pokemonResponse.isSuccessful) {
            return pokemonResponse.body() as List<PokemonSpecies>?
        }
        return null
    }

}