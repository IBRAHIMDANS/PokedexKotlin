package com.example.pokedex.pokemonDetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.network.Api
import kotlinx.coroutines.launch

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        val name = findViewById<TextView>(R.id.textViewName)
        val height = findViewById<TextView>(R.id.textViewHeight)
        val weight = findViewById<TextView>(R.id.textViewWeight)
        val imagePokemon = findViewById<ImageView>(R.id.imageViewPokemonDetails)

        val pokemon: String? = intent?.getSerializableExtra("pokemon") as? String

        if(pokemon != null){
            lifecycleScope.launch {
                val pokemonInfo = Api.pokemonService.getByName(pokemon).body()!!
                val glide = Glide.with(applicationContext)
                name.setText(pokemonInfo.id + ". " + pokemonInfo.name.toUpperCase())
                height.setText(pokemonInfo.height.toString())
                weight.setText(pokemonInfo.weight.toString())
                //image prise sur pokeres pour un rendu plus beau
                glide.load("https://pokeres.bastionbot.org/images/pokemon/${pokemonInfo.id}.png").into(imagePokemon)
            }
        }
    }
}