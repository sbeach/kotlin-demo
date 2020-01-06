package dev.sbeach.kotlindemo.ui

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dev.sbeach.kotlindemo.R
import dev.sbeach.kotlindemo.data.manager.CharacterViewModel
import kotlinx.android.synthetic.main.activity_character_sheet.*

class CharacterSheetActivity : AppCompatActivity() {

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_sheet)

        val adapter = AbilityAdapter(this)
        characterAbilityScores.adapter = adapter
        characterAbilityScores.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        val id = intent.getIntExtra(EXTRA_ID, -1)
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        characterViewModel.allCharacters.observe(this, Observer { characters ->
            characters?.let { charList ->
                val character = charList.filter { character -> character.id == id }[0]
                adapter.setAbilities(character.abilities)
                title = character.name
            }
        })
    }

    companion object {
        const val EXTRA_ID = "dev.sbeach.kotlindemo.CHARACTER_ID"
    }
}
