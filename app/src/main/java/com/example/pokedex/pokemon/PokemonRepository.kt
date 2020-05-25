package com.example.pokedex.pokemon

import com.example.pokedex.network.Api

class PokemonRepository {
    private val pokemonService = Api.pokemonService

    suspend fun getPokemonList(): List<PokemonSpecies>? {
        val pokemonResponse = pokemonService.getAll()
        if (pokemonResponse.isSuccessful) {
            return pokemonResponse.body()
        }
        return null
    }


    suspend fun getPokemonInfo(id: String): PokemonInfo? {
        val usersResponse = pokemonService.getById(id)
        if (usersResponse.isSuccessful) {
            return usersResponse.body()
        }
        return null
    }

}