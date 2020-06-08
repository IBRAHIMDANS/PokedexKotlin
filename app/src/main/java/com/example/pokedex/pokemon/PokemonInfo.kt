package com.example.pokedex.pokemon

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
data class PokemonInfo(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "weight")
    val weight: Int,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "sprites")
    val sprites: Sprites
) : Serializable, Parcelable

@Parcelize
data class Sprites(
    @field:Json(name = "front_default")
    val front_default: String?
) : Serializable, Parcelable

@Parcelize
data class PokemonSpecies(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
) : Serializable, Parcelable

data class PokeListResponse(@Json(name = "results") val results : List<PokemonSpecies>)