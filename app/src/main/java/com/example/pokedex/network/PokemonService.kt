package com.example.pokedex.network

import com.example.pokedex.pokemon.PokemonInfo
import com.example.pokedex.pokemon.PokemonSpecies
import com.example.pokedex.pokemon.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon?limit=100")
    suspend fun getAll(): Response<PokemonResponse<PokemonSpecies?>>

    @GET("pokemon/{id}")
    suspend fun getById(@Path("id") id:String? ): Response<PokemonInfo>
}