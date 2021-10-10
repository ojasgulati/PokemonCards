package com.example.pokemoncards.search.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonCardData(
    val count: Int?,
    val `data`: List<Data>,
    val page: Int?,
    val pageSize: Int?,
    val totalCount: Int?
) : Parcelable {
    @Parcelize
    data class Data(
        val abilities: List<Ability>?,
        val attacks: List<Attack>?,
        val convertedRetreatCost: Int?,
        val evolvesFrom: String?,
        val hp: Long?,
        val id: String?,
        val images: Images?,
        val level: Long?,
        val name: String?,
        val number: String?,
        val rarity: String?,
        val resistances: List<Resistance>?,
        val retreatCost: List<String>?,
        val subtypes: List<String>?,
        val supertype: String?,
        val types: List<String>?,
        val weaknesses: List<Weaknesse>?
    ) : Parcelable {
        @Parcelize
        data class Ability(
            val name: String?,
            val text: String?,
            val type: String?
        ) : Parcelable

        @Parcelize
        data class Attack(
            val convertedEnergyCost: Int?,
            val cost: List<String>?,
            val damage: String?,
            val name: String?,
            val text: String?
        ) : Parcelable

        @Parcelize
        data class Images(
            val large: String?,
            val small: String?
        ) : Parcelable

        @Parcelize
        data class Resistance(
            val type: String?,
            val value: String?
        ) : Parcelable

        @Parcelize
        data class Weaknesse(
            val type: String?,
            val value: String?
        ) : Parcelable
    }
}