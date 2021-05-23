package com.example.brillet_esiea.prensentation.api

import com.example.brillet_esiea.prensentation.list.Pokemon

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val results: List<Pokemon>
)