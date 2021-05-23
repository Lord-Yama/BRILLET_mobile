package com.example.brillet_esiea.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brillet_esiea.R
import com.example.brillet_esiea.presentation.Singletons
import com.example.brillet_esiea.presentation.api.PokemonDetailResponse
import com.example.brillet_esiea.presentation.api.PokemonListResponse
import com.example.brillet_esiea.presentation.api.PokemonSlot
import com.example.brillet_esiea.presentation.api.PokemonType
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PokemonDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewHeight: TextView
    private lateinit var textViewType: TextView
    private lateinit var textViewStats: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.pokemon_detail_name)
        textViewHeight = view.findViewById(R.id.pokemon_detail_height)
        textViewType = view.findViewById(R.id.pokemon_detail_type)
        textViewStats = view.findViewById(R.id.pokemon_detail_stats)

        callApi()
    }

    private fun callApi(){
        val id:Int = arguments?.getInt("pokemonId") ?: -1
        Singletons.pokemonApi.getPokemonDetail(id).enqueue(object : retrofit2.Callback<PokemonDetailResponse>{
            override fun onFailure(
                call: Call<PokemonDetailResponse>,
                t: Throwable
            ){}
            override fun onResponse(
                call: Call<PokemonDetailResponse>,
                response: Response<PokemonDetailResponse>
            ){
                if(response.isSuccessful && response.body() != null){
                    textViewName.text = response.body()!!.name
                    textViewHeight.text = response.body()!!.height.toString()
                    textViewType.text = response.body()!!.types.toString()
                    textViewStats.text = response.body()!!.stats.toString()
                }
            }
        })

    }
}
