package dev.sbeach.kotlindemo.ui

import android.app.Activity
import android.content.Intent
import kotlinx.android.synthetic.main.activity_new_character.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import dev.sbeach.kotlindemo.R

class NewCharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (editCharacterName.text.isBlank()) {
                Snackbar.make(editCharacterName, "Name cannot be blank", Snackbar.LENGTH_SHORT).show()
            }
            else {
                replyIntent.putExtra(EXTRA_NAME, editCharacterName.text.toString())
                replyIntent.putExtra(EXTRA_STR, score(editCharStr.text.toString()))
                replyIntent.putExtra(EXTRA_DEX, score(editCharDex.text.toString()))
                replyIntent.putExtra(EXTRA_CON, score(editCharCon.text.toString()))
                replyIntent.putExtra(EXTRA_INT, score(editCharInt.text.toString()))
                replyIntent.putExtra(EXTRA_WIS, score(editCharWis.text.toString()))
                replyIntent.putExtra(EXTRA_CHA, score(editCharCha.text.toString()))
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }

    fun score(input: String): Int {
        return if (input.isBlank()) {
            8
        } else {
            Integer.valueOf(input)
        }
    }

    companion object {
        const val EXTRA_NAME = "dev.sbeach.kotlindemo.CHARACTER_NAME"
        const val EXTRA_STR = "dev.sbeach.kotlindemo.CHARACTER_STR"
        const val EXTRA_DEX = "dev.sbeach.kotlindemo.CHARACTER_DEX"
        const val EXTRA_CON = "dev.sbeach.kotlindemo.CHARACTER_CON"
        const val EXTRA_INT = "dev.sbeach.kotlindemo.CHARACTER_INT"
        const val EXTRA_WIS = "dev.sbeach.kotlindemo.CHARACTER_WIS"
        const val EXTRA_CHA = "dev.sbeach.kotlindemo.CHARACTER_CHA"
    }
}
