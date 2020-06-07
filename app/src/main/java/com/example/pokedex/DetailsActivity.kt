package com.example.pokedex

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pokedex.network.Api
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val name = findViewById<TextView>(R.id.textViewName)
        val height = findViewById<TextView>(R.id.textViewHeight)
        val weight = findViewById<TextView>(R.id.textViewWeight)
        val imagePokemon = findViewById<ImageView>(R.id.imageViewPokemonDetails)

        val task: String? = intent?.getSerializableExtra("task") as? String

        if(task != null){
            lifecycleScope.launch {
                val userInfo = Api.pokemonService.getByName(task).body()!!
                val glide = Glide.with(applicationContext)
                name.setText(userInfo.name.toString())
                height.setText(userInfo.weight.toString())
                weight.setText(userInfo.weight.toString())
                glide.load(userInfo.front_default).circleCrop().into(imagePokemon)
            }
        }
    }
}