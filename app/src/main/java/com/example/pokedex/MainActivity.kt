package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokedex.network.Api
import kotlinx.coroutines.launch
import  com.example.pokedex.pokemon.*
import kotlinx.android.synthetic.main.activity_pokemons_list.*
import java.io.Serializable
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val pokemonWebService = Api.pokemonService
    private val pokemonRepository = PokemonRepository()
    var adapter = PokemonListAdapter()

    private val viewModelTask by lazy {
        ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemons_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        viewModelTask.pokemonList.observe(this, Observer { newList ->
            adapter.taskList = newList.orEmpty()
            adapter.notifyDataSetChanged()
        })

        savedInstanceState?.getParcelableArrayList<PokemonSpecies>("taskList")?.let { savedList ->
            adapter.taskList = savedList
            adapter.notifyDataSetChanged()
        }

        adapter.onDetailClickListener = { task ->
            val intent = Intent(applicationContext, DetailsActivity::class.java)
            intent.putExtra("task", task.name as Serializable)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModelTask.loadAllPokemons()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("taskList", viewModelTask.pokemonList.value as? ArrayList<PokemonSpecies>)
    }

/*    suspend fun getInfo(): List<PokemonSpecies>? {
        val tasksResponse = pokemonWebService.getAll()
        return if (tasksResponse.isSuccessful) {
            tasksResponse.body()
        } else null
    }*/
}
