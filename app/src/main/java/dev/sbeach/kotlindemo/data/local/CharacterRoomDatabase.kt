package dev.sbeach.kotlindemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.sbeach.kotlindemo.data.model.Ability
import dev.sbeach.kotlindemo.data.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Character::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterRoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    private class CharacterDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    if (database.characterDao().count() == 0) {
                        populateDatabase(database.characterDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(characterDao: CharacterDao) {
            var character = Character(name = "Cinder"
                ,strength = Ability(Ability.Type.STR, 8)
                ,dexterity = Ability(Ability.Type.DEX, 14)
                ,constitution = Ability(Ability.Type.CON,16)
                ,intelligence = Ability(Ability.Type.INT,20)
                ,wisdom = Ability(Ability.Type.WIS,12)
                ,charisma = Ability(Ability.Type.CHA, 10))
            characterDao.insert(character)
            character = Character(name = "Nesalli"
                ,strength = Ability(Ability.Type.STR, 8)
                ,dexterity = Ability(Ability.Type.DEX, 14)
                ,constitution = Ability(Ability.Type.CON,13)
                ,intelligence = Ability(Ability.Type.INT,14)
                ,wisdom = Ability(Ability.Type.WIS,10)
                ,charisma = Ability(Ability.Type.CHA, 20))
            characterDao.insert(character)
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CharacterRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CharacterRoomDatabase {
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterRoomDatabase::class.java,
                    "character_db"
                )
                    .addCallback(
                        CharacterDatabaseCallback(
                            scope
                        )
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
