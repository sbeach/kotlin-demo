package dev.sbeach.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.sbeach.kotlindemo.R
import dev.sbeach.kotlindemo.data.model.Ability
import dev.sbeach.kotlindemo.data.model.Character

class AbilityAdapter internal constructor(context: Context) : RecyclerView.Adapter<AbilityAdapter.CharacterSheetViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var abilities = emptyList<Ability>() // Cached copy of abilities

    inner class CharacterSheetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val abilityName: TextView = itemView.findViewById(R.id.itemAbilityName)
        val abilityScore: TextView = itemView.findViewById(R.id.itemAbilityScore)
        val abilityModifier: TextView = itemView.findViewById(R.id.itemAbilityModifier)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterSheetViewHolder {
        val itemView = inflater.inflate(R.layout.item_ability, parent, false)
        return CharacterSheetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterSheetViewHolder, position: Int) {
        val current = abilities[position]
        holder.abilityName.text = current.type.formatted
        holder.abilityScore.text = current.score.toString()
        holder.abilityModifier.text = if (current.modifier <= 0) current.modifier.toString() else "+${current.modifier}"
    }

    internal fun setAbilities(abilities: List<Ability>) {
        this.abilities = abilities
        notifyDataSetChanged()
    }

    override fun getItemCount() = abilities.size
}
