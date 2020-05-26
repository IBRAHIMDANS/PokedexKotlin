package com.example.pokedex

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pokedex.network.Api
import kotlinx.coroutines.launch
import  com.example.pokedex.pokemon.*

class MainActivity : AppCompatActivity() {

    private val pokemonWebService = Api.pokemonService
    private val pokemonRepository = PokemonRepository()

    private val viewModelTask by lazy {
        ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemons_list)
        /*val glide = Glide.with(this)
        lifecycleScope.launch {
            val pokemonInfo = getInfo()
        }*/
    }

    override fun onResume() {
        super.onResume()
        val glide = Glide.with(this)
        lifecycleScope.launch {
            val userInfo = Api.pokemonService.getAll().body()!!
        }
        viewModelTask.loadAllPokemons()
    }

/*    suspend fun getInfo(): List<PokemonSpecies>? {
        val tasksResponse = pokemonWebService.getAll()
        return if (tasksResponse.isSuccessful) {
            tasksResponse.body()
        } else null
    }*/
}
