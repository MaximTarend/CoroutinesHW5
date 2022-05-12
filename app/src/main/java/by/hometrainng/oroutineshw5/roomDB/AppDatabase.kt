package by.hometrainng.oroutineshw5.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import by.hometrainng.oroutineshw5.model.Character

@Database(entities = [Character::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}