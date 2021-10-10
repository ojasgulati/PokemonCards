package com.example.pokemoncards.search.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.example.pokemoncards.databinding.ActivitySearchBinding
import com.example.pokemoncards.pokemoncard.PokemonCardActivity
import com.example.pokemoncards.search.domain.SearchViewModel
import com.example.pokemoncards.search.model.PokemonCardData
import com.example.pokemoncards.search.model.PokemonSort
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var activitySearchBinding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        val adapter = SearchAdapter(::clickItem)
        activitySearchBinding.rvPokemonCard.adapter = adapter
        viewModel.pokemonCardLiveData.observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }


        with(activitySearchBinding.spinnerSortPokemon) {
            this.adapter = ArrayAdapter(
                this@SearchActivity,
                android.R.layout.simple_list_item_1,
                PokemonSort.values()
            )
            onItemSelectedListener = this@SearchActivity
        }

        activitySearchBinding.svPokemon.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filter(newText)
                return false
            }
        })
    }

    private fun clickItem(data: PokemonCardData.Data) {
        val intent = Intent(this, PokemonCardActivity::class.java)
        intent.putExtra("extra_item", data)
        startActivity(intent)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p2) {
            1 -> viewModel.sortByHP()
            2 -> viewModel.sortByLevel()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}