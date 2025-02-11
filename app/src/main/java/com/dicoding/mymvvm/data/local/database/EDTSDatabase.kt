package com.dicoding.mymvvm.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.dicoding.mymvvm.data.local.dao.DesignTeamDAO
import com.dicoding.mymvvm.data.local.entity.DesignTeam

// @Database annotation defines the Room database configuration.
// The "entities" parameter lists the entities associated with the database.
// "version" represents the database schema version, and "exportSchema" defines whether schema history should be exported.
@Database(
    entities = [DesignTeam::class], version = 1, exportSchema = false
)
abstract class EDTSDatabase : RoomDatabase() {

    // Abstract method to provide access to the DesignTeamDAO.
    abstract fun designTeamDao(): DesignTeamDAO

    companion object {
        // Holds a reference to the singleton instance of the database.
        private lateinit var instance: EDTSDatabase

        // Ensures thread safety by synchronizing the database instance creation.
        @Synchronized
        fun getInstance(context: Context): EDTSDatabase {
            instance = databaseBuilder(
                context.applicationContext,
                EDTSDatabase::class.java, "EDTS_Database"
            )
                // Allows destructive migration as a fallback strategy.
                .fallbackToDestructiveMigration()
                .build()
            return instance
        }
    }
}