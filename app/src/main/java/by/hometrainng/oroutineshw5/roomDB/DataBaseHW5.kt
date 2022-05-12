package by.hometrainng.oroutineshw5.roomDB

import android.app.Application
import android.content.Context
import androidx.room.Room

class DataBaseHW5: Application() {

    private var _appDatabase: AppDatabase? = null
    val appDatabase get() = requireNotNull(_appDatabase)

    override fun onCreate() {
        super.onCreate()
        _appDatabase = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database.db"
        ).build()
    }
}

val Context.appDatabase: AppDatabase
    get() = when (this) {
        is DataBaseHW5 -> appDatabase
        else -> applicationContext.appDatabase
    }
