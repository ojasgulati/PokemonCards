package com.example.pokemoncards.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemoncards.databinding.LayoutPokemonCardBinding
import com.example.pokemoncards.search.model.PokemonCardData
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso

class SearchAdapter(private val clickListener: (PokemonCardData.Data) -> Unit) :
    ListAdapter<PokemonCardData.Data, SearchAdapter.SearchViewHolder>(SearchDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutPokemonCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindTo(getItem(position), clickListener)
    }

    inner class SearchViewHolder(
        private val binding: LayoutPokemonCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(data: PokemonCardData.Data, clickListener: (PokemonCardData.Data) -> Unit) {
            binding.tvName.text = data.name
            binding.tvHeart.text = data.hp?.toString()
            binding.tvTrophy.text = data.level?.toString()
            binding.chipGroup.removeAllViews()
            data.types?.forEach {
                val chip = Chip(binding.root.context)
                chip.text = it
                binding.chipGroup.addView(chip)
            }
            Picasso.get()
                .load(data.images?.small)
                .into(binding.ivPokemonCard)
            binding.root.setOnClickListener{
                clickListener(data)
            }
        }
    }
}

class SearchDiffUtils : DiffUtil.ItemCallback<PokemonCardData.Data>() {
    override fun areItemsTheSame(
        oldItem: PokemonCardData.Data,
        newItem: PokemonCardData.Data
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: PokemonCardData.Data,
        newItem: PokemonCardData.Data
    ): Boolean =
        oldItem == newItem

}