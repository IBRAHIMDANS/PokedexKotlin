package com.example.pokedex

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class PokemonInfo(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String
): Serializable, Parcelable