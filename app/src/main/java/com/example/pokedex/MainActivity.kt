package com.example.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pokedex.network.Api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import  com.example.pokedex.pokemon.*

class MainActivity : AppCompatActivity() {

    private val pokemonWebService = Api.pokemonService
    private val pokemonRepository = PokemonRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            val pokemonInfo = getInfo()
        }
    }

    suspend fun getInfo(): List<PokemonSpecies>? {
        val tasksResponse = pokemonWebService.getAll()
        return if (tasksResponse.isSuccessful) {
            tasksResponse.body()
        } else null
    }
}
