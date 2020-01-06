package dev.sbeach.kotlindemo.data.manager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dev.sbeach.kotlindemo.data.local.CharacterRoomDatabase
import dev.sbeach.kotlindemo.data.model.Character
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: CharacterRepository
    // LiveData gives us updated characters when they change.
    val allCharacters: LiveData<List<Character>>

    init {
        // Gets reference to CharacterDao from CharacterRoomDatabase to construct the correct CharacterRepository.
        val characterDao = CharacterRoomDatabase.getDatabase(application, viewModelScope).characterDao()
        repository = CharacterRepository(characterDao)
        allCharacters = repository.allCharacters
    }

    fun insert(character: Character) = viewModelScope.launch {
        repository.insert(character)
    }
}
