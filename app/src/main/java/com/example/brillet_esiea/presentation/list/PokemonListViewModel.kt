package com.example.brillet_esiea.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brillet_esiea.presentation.Singletons
import com.example.brillet_esiea.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel : ViewModel() {
    val pokeList : MutableLiveData<PokemonModel> = MutableLiveData()

    init{
        callApi()
    }
    private fun callApi(){
        pokeList.value = PokemonLoader
        Singletons.pokemonApi.getPokemonList("9").enqueue(object : Callback<PokemonListResponse>{
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable){
                pokeList.value = PokemonError
            }
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>){
                if(response.isSuccessful && response.body() != null){
                    val pokemonResponse : PokemonListResponse = response.body()!!
                    pokeList.value = PokemonSuccess(pokemonResponse.results)
                }else{
                    pokeList.value = PokemonError
                }
            }
        })
    }
}