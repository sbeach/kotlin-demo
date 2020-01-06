package dev.sbeach.kotlindemo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.sbeach.kotlindemo.data.model.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(character: Character)

    @Query("SELECT * FROM character ORDER BY character.name ASC")
    fun getAll(): LiveData<List<Character>>

    @Query("SELECT * FROM character WHERE id = (:id)")
    fun findById(id: Int): LiveData<Character>

    @Query("SELECT * FROM character WHERE name LIKE (:name) LIMIT 1")
    suspend fun findByName(name: String): Character

    @Delete
    suspend fun delete(character: Character)

    @Query("DELETE FROM character")
    suspend fun deleteAll()

    // Used to check if table has any contents; if 0, table is empty
    @Query("SELECT count(*) FROM character")
    suspend fun count(): Int
}
