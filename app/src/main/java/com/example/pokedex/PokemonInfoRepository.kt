package com.example.pokedex

import com.example.pokedex.network.Api

class PokemonInfoRepository {
    private val pokemonService = Api.pokemonService

    suspend fun getPokemonList(): List<PokemonInfo>? {
        val pokemonResponse = pokemonService.getAll()
        if (pokemonResponse.isSuccessful) {
            return pokemonResponse.body() as List<PokemonInfo>
        }
        return null
    }

}