package dev.sbeach.kotlindemo.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Character(
    var name: String,
    var strength: Ability = Ability(Ability.Type.STR),
    var dexterity: Ability = Ability(Ability.Type.DEX),
    var constitution: Ability = Ability(Ability.Type.CON),
    var intelligence: Ability = Ability(Ability.Type.INT),
    var wisdom: Ability = Ability(Ability.Type.WIS),
    var charisma: Ability = Ability(Ability.Type.CHA)
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @Ignore
    val abilities: List<Ability> = arrayListOf(
        strength, dexterity, constitution, intelligence, wisdom, charisma
    )
}
