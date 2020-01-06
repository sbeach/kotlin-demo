package dev.sbeach.kotlindemo.data.manager

import androidx.lifecycle.LiveData
import dev.sbeach.kotlindemo.data.local.CharacterDao
import dev.sbeach.kotlindemo.data.model.Character

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CharacterRepository(private val characterDao: CharacterDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCharacters: LiveData<List<Character>> = characterDao.getAll()

    suspend fun insert(character: Character) {
        characterDao.insert(character)
    }
}
