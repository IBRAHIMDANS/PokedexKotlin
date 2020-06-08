package com.example.pokedex.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_pokemon.view.*
import java.net.URL
import kotlin.properties.Delegates

class PokemonListAdapter  : RecyclerView.Adapter<PokemonListAdapter.TaskViewHolder>() {

    // Déclaration d'une lambda comme variable:
    var onDetailClickListener: ((PokemonSpecies) -> Unit)? = null

    var taskList: List<PokemonSpecies> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: PokemonSpecies) {
            // C'est ici qu'on reliera les données et les listeners une fois l'adapteur implémenté
            val glide = Glide.with(itemView.context)
            itemView.textViewName.text = task.name
            itemView.textViewUrl.text = task.url
            val pathsegm = task.url.toUri().lastPathSegment
            glide.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pathsegm.png").circleCrop().into(itemView.imgPokemon)

            val pokemonLayout = itemView.findViewById<ConstraintLayout>(R.id.pokemonLayout)

            pokemonLayout.setOnClickListener{
                onDetailClickListener?.invoke(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }
}