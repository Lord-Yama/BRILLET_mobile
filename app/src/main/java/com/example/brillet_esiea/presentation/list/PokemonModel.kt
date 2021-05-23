package com.example.brillet_esiea.presentation.list

sealed class PokemonModel

data class PokemonSuccess(val pokeList: List<Pokemon>) : PokemonModel()
object PokemonLoader : PokemonModel()
object PokemonError : PokemonModel()