package com.example.pokedex

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pokedex.network.Api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val userWebService = Api.pokemonService

    //Liste des pokemons
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val glide = Glide.with(this)
        lifecycleScope.launch {
            val pokemonInfo = getInfo()
            name.setText(pokemonInfo?.name)
        }
    }


    suspend fun getInfo(): PokemonInfo? {
        val tasksResponse = userWebService.getById("1")
        return if (tasksResponse.isSuccessful) {
            name.setText(tasksResponse.body()!!.name)
            tasksResponse.body()
        } else null
    }
}
