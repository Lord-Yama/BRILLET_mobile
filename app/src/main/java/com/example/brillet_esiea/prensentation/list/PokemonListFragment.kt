package com.example.brillet_esiea.prensentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brillet_esiea.R
import com.example.brillet_esiea.prensentation.api.PokemonApi
import com.example.brillet_esiea.prensentation.api.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = PokemonAdapter(listOf(), ::onClickedPokemon)

    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerview)

        recyclerView.apply {
            layoutManager = this@PokemonListFragment.layoutManager
            adapter = this@PokemonListFragment.adapter
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonApi: PokemonApi = retrofit.create(PokemonApi::class.java)

        pokemonApi.getPokemonList("50").enqueue(object : Callback<PokemonResponse>{
            override fun onFailure(
                call: Call<PokemonResponse>,
                t: Throwable
            ){
                //TODO("not implemented")
            }

            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ){
                if(response.isSuccessful && response.body() != null){
                    val pokemonResponse : PokemonResponse =  response.body()!!
                    adapter.updatelist(pokemonResponse.results)
                }
            }
        })
    }

    private fun onClickedPokemon(pokemon: Pokemon) {
        findNavController().navigate(R.id.navigateToPokemonDetailFragment)
    }
}