package com.example.pokedex.pokemon

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class PokemonInfo(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "weight")
    val weight: Int,

    @field:Json(name = "height")
    val height: Int
) : Serializable, Parcelable

data class PokemonSpecies(
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "url")
    val url: String?
)