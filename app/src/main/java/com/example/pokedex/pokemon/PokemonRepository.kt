package com.example.pokedex.pokemon

import com.example.pokedex.network.Api
import com.example.pokedex.pokemon.*

class PokemonRepository {
    private val pokemonService = Api.pokemonService

    suspend fun getPokemonList(): PokemonResponse<PokemonSpecies?>? {
        val pokemonResponse = pokemonService.getAll()
        if (pokemonResponse.isSuccessful) {
            return pokemonResponse.body()
        }
        return null
    }

}