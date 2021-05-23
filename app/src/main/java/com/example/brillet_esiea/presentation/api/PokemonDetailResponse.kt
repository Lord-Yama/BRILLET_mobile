package com.example.brillet_esiea.presentation.api

data class PokemonDetailResponse (
    val name: String,
    val height: Int,
    val types: List<PokemonSlot>,
    val stats: List<PokemonStats>
)

data class PokemonSlot(
    val type: PokemonType
)

data class PokemonType(
    val name: String
)

data class PokemonStats(
    val base_stat: Int,
    val stat: PokemonStat
)
data class PokemonStat(
    val name: String
)