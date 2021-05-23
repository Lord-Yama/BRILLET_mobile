package com.example.brillet_esiea.presentation.api

import com.example.brillet_esiea.presentation.list.Pokemon

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val results: List<Pokemon>
)