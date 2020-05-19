package com.example.pokedex.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {
    private val moshi = Moshi.Builder().build()

    val pokemonService: PokemonService by lazy { retrofit.create(PokemonService::class.java) }

    private const val BASE_URL = "https://pokeapi.co/api/v2"
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}