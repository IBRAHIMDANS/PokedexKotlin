package com.example.pokedex.pokemonsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.pokemon.PokemonSpecies
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlin.properties.Delegates

class PokemonListAdapter  : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    var onDetailClickListener: ((PokemonSpecies) -> Unit)? = null

    var pokemonList: List<PokemonSpecies> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: PokemonSpecies) {

            val glide = Glide.with(itemView.context)
            itemView.textViewName.text = task.name.toUpperCase()
            val pathsegm = task.url.toUri().lastPathSegment
            glide.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pathsegm.png").circleCrop().into(itemView.imgPokemon)

            val pokemonLayout = itemView.findViewById<ConstraintLayout>(R.id.pokemonLayout)

            pokemonLayout.setOnClickListener{
                onDetailClickListener?.invoke(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }
}