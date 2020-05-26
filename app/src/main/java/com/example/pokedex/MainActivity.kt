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


    private val viewModelTask by lazy {
        ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemons_list)
    }

    override fun onResume() {
        super.onResume()
        viewModelTask.loadPokemons()
    }
}
