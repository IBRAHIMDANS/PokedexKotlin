package com.example.pokedex.network

import com.example.pokedex.pokemon.PokeListResponse
import com.example.pokedex.pokemon.PokemonInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon?limit=100")
    suspend fun getAll(): Response<PokeListResponse>

    @GET("pokemon/{id}")
    suspend fun getById(@Path("id") id:String? ): Response<PokemonInfo>
}