package com.hihasan.shaketoinform.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hihasan.shaketoinform.constants.DatabaseConstants
import com.hihasan.shaketoinform.data.dao.BugDao
import com.hihasan.shaketoinform.data.dao.SuggestionsDao
import com.hihasan.shaketoinform.data.entity.BugEntity
import com.hihasan.shaketoinform.data.entity.SuggestionEntity

@Database(
    entities = [
        BugEntity::class,
        SuggestionEntity::class
    ],
    version = DatabaseConstants.DATABASE_VERSION,
)

abstract class BaseDatabase : RoomDatabase() {
    abstract val bugDao : BugDao
    abstract val suggestionDao : SuggestionsDao

    companion object {
        private var INSTANCE: BaseDatabase? = null

        fun getDatabase(ctx: Context): BaseDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        ctx, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }


}
