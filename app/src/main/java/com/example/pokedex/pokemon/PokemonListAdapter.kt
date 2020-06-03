package com.example.pokedex.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlin.properties.Delegates

class PokemonListAdapter  : RecyclerView.Adapter<PokemonListAdapter.TaskViewHolder>() {

    // Déclaration d'une lambda comme variable:
    var onEditClickListener: ((PokemonSpecies) -> Unit)? = null
    var onDeleteClickListener: ((PokemonSpecies) -> Unit)? = null

    var taskList: List<PokemonSpecies> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: PokemonSpecies) {
            // C'est ici qu'on reliera les données et les listeners une fois l'adapteur implémenté
            itemView.textViewName.text = task.name
            itemView.textViewUrl.text = task.url
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