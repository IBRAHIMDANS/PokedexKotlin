package com.example.pokedex

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pokedex.network.Api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val pokemonWebService = Api.pokemonService


    private val viewModelTask by lazy {
        ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    //Liste des pokemons
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemons_list)
        val glide = Glide.with(this)
        lifecycleScope.launch {
            val pokemonInfo = getInfo()
        }
    }

    suspend fun getInfo(): List<PokemonInfo?>? {
        val tasksResponse = pokemonWebService.getAll()
        return if (tasksResponse.isSuccessful) {
            tasksResponse.body()
        } else null
    }
}
