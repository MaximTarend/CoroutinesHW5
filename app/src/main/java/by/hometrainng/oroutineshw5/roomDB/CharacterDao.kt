package by.hometrainng.oroutineshw5.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import by.hometrainng.oroutineshw5.model.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<Character>

    @Insert(onConflict = REPLACE)
    suspend fun insertCharacters(characters: List<Character>)
}