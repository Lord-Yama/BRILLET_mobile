package com.example.brillet_esiea.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brillet_esiea.R
import com.example.brillet_esiea.presentation.Singletons
import com.example.brillet_esiea.presentation.api.PokemonListResponse
import com.example.brillet_esiea.presentation.list.PokemonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = PokemonAdapter(listOf(), ::onClickedPokemon)

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
            layoutManager = LinearLayoutManager(context)
            adapter = this@PokemonListFragment.adapter
        }

        Singletons.pokemonApi.getPokemonList("10").enqueue(object : Callback<PokemonListResponse>{
            override fun onFailure(
                call: Call<PokemonListResponse>,
                t: Throwable
            ){
                //TODO("not implemented")
            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                listResponse: Response<PokemonListResponse>
            ){
                if(listResponse.isSuccessful && listResponse.body() != null){
                    val pokemonListResponse : PokemonListResponse =  listResponse.body()!!
                    adapter.updatelist(pokemonListResponse.results)
                }
            }
        })
    }

    private fun onClickedPokemon(id: Int) {
        findNavController().navigate(R.id.navigateToPokemonDetailFragment, bundleOf("pokemonId" to (id+1)))
    }
}