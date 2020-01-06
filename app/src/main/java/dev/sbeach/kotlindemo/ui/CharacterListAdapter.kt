package dev.sbeach.kotlindemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import dev.sbeach.kotlindemo.R
import dev.sbeach.kotlindemo.data.model.Character

class CharacterListAdapter internal constructor(val context: Context) : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var characters = emptyList<Character>() // Cached copy of characters

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterItemView: TextView = itemView.findViewById(R.id.itemCharacterName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = inflater.inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = characters[position]
        holder.characterItemView.text = current.name
        holder.itemView.setOnClickListener{ navigateToCharacterSheet(current) }
    }

    fun navigateToCharacterSheet(character: Character) {
        val intent = Intent(context, CharacterSheetActivity::class.java)
        intent.putExtra(CharacterSheetActivity.EXTRA_ID, character.id)
        startActivity(context, intent, null)
    }

    internal fun setCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size
}
