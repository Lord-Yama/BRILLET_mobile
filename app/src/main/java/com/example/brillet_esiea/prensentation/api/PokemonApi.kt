package com.example.brillet_esiea.prensentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi{
    @GET("pokemon")
    fun getPokemonList(@Query("limit")limit : String): Call<PokemonResponse>
}