package com.example.pokemoncards.pokemoncard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemoncards.R
import com.example.pokemoncards.databinding.ActivityPokemonCardBinding
import com.example.pokemoncards.search.model.PokemonCardData
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonCardActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityPokemonCardBinding
    private val viewModel: PokemonCardViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPokemonCardBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val data = intent.getParcelableExtra<PokemonCardData.Data>("extra_item")
        data?.run {
            mBinding.tvName.text = this.name

            Picasso.get()
                .load(data.images?.small)
                .into(mBinding.ivPokemonCard)

            mBinding.level.key.text = getString(R.string.level)
            mBinding.level.value.text = this.level?.toString()

            mBinding.hp.key.text = getString(R.string.hp)
            mBinding.hp.value.text = this.hp?.toString()

            mBinding.types.key.text = getString(R.string.types)
            mBinding.types.value.text = this.types?.joinToString(separator = ",")

            mBinding.subTypes.key.text = getString(R.string.subTypes)
            mBinding.subTypes.value.text = this.subtypes?.joinToString(separator = ",")

            mBinding.attacks.key.text = getString(R.string.attacks)
            mBinding.attacks.value.text = this.attacks?.joinToString(separator = ","){
                "${it.name}"
            }

            mBinding.weakness.key.text = getString(R.string.weakness)
            mBinding.weakness.value.text = this.weaknesses?.joinToString(separator = ","){
                "${it.type}"
            }

            mBinding.abilities.key.text = getString(R.string.abilities)
            mBinding.abilities.value.text = this.abilities?.joinToString(separator = ","){
                "${it.name}"
            }

            mBinding.resistances.key.text = getString(R.string.resistances)
            mBinding.resistances.value.text = this.resistances?.joinToString(separator = ","){
                "${it.type}"
            }

        }
    }
}