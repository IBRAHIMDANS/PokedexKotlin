package com.example.pokedex

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pokedex.network.Api
import com.example.pokedex.pokemon.PokemonSpecies
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val name = findViewById<TextView>(R.id.name)

        val task: String? = intent?.getSerializableExtra("task") as? String

        if(task != null){
            lifecycleScope.launch {
                val userInfo = Api.pokemonService.getByName(task).body()!!
                name.setText(userInfo.weight.toString())
            }
        }
    }
}