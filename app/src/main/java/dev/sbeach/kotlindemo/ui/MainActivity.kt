package dev.sbeach.kotlindemo.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.sbeach.kotlindemo.R
import dev.sbeach.kotlindemo.data.manager.CharacterViewModel
import dev.sbeach.kotlindemo.data.model.Ability
import dev.sbeach.kotlindemo.data.model.Character
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newCharacterActivityRequestCode = 1
    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CharacterListAdapter(this)
        mainCharacterList.adapter = adapter
        mainCharacterList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mainAddCharacter.setOnClickListener { startNewCharacterActivity() }

        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        characterViewModel.allCharacters.observe(this, Observer { characters ->
            characters?.let {
                adapter.setCharacters(it) }
        })
    }

    fun startNewCharacterActivity() {
        val intent = Intent(this, NewCharacterActivity::class.java)
        startActivityForResult(intent, newCharacterActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newCharacterActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val name = data.getStringExtra(NewCharacterActivity.EXTRA_NAME) ?: "Unnamed"
                val strength      = data.getIntExtra(NewCharacterActivity.EXTRA_STR, 8)
                val dexterity     = data.getIntExtra(NewCharacterActivity.EXTRA_DEX, 8)
                val constitution  = data.getIntExtra(NewCharacterActivity.EXTRA_CON, 8)
                val intelligence  = data.getIntExtra(NewCharacterActivity.EXTRA_INT, 8)
                val wisdom        = data.getIntExtra(NewCharacterActivity.EXTRA_WIS, 8)
                val charisma      = data.getIntExtra(NewCharacterActivity.EXTRA_CHA, 8)

                val character = Character(name
                    ,Ability(Ability.Type.STR, strength)
                    ,Ability(Ability.Type.DEX, dexterity)
                    ,Ability(Ability.Type.CON, constitution)
                    ,Ability(Ability.Type.INT, intelligence)
                    ,Ability(Ability.Type.WIS, wisdom)
                    ,Ability(Ability.Type.CHA, charisma))
                characterViewModel.insert(character)
            }
        }
        else {
            Snackbar.make(mainAddCharacter,
                R.string.empty_not_saved, Snackbar.LENGTH_LONG).show()
        }
    }
}
