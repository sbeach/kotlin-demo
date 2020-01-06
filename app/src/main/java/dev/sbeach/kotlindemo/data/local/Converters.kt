package dev.sbeach.kotlindemo.data.local

import androidx.room.TypeConverter
import dev.sbeach.kotlindemo.data.model.Ability
import dev.sbeach.kotlindemo.data.model.Ability.Type.*
import org.json.JSONObject
import java.lang.IllegalArgumentException

class Converters {

    @TypeConverter
    fun abilityToJson(ability: Ability): String {
        return "{\"type\":${ability.type.formatted}, \"score\":${ability.score}}"
    }

    @TypeConverter
    fun jsonToAbility(json: String): Ability {
        val jsonObject = JSONObject(json)
        return Ability(stringToType(jsonObject.getString("type")), jsonObject.getInt("score"))
    }

    @TypeConverter
    fun stringToType(type: String): Ability.Type {
        return when (type) {
            STR.formatted -> STR
            DEX.formatted -> DEX
            CON.formatted -> CON
            INT.formatted -> INT
            WIS.formatted -> WIS
            CHA.formatted -> CHA
            else -> throw IllegalArgumentException("Could not recognize ability type")
        }
    }

    @TypeConverter
    fun typeToString(type: Ability.Type): String {
        return type.formatted
    }
}
