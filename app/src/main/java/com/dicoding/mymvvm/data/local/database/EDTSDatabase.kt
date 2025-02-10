package com.dicoding.mymvvm.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.dicoding.mymvvm.data.local.dao.DesignTeamDAO
import com.dicoding.mymvvm.data.local.entity.DesignTeam


@Database(
    entities = [DesignTeam::class], version = 1, exportSchema = false
)

abstract class EDTSDatabase : RoomDatabase() {

    abstract fun designTeamDao(): DesignTeamDAO

    companion object {
        private lateinit var instance: EDTSDatabase

        @Synchronized
        fun getInstance(context: Context): EDTSDatabase {
            instance = databaseBuilder(
                context.applicationContext,
                EDTSDatabase::class.java, "EDTS_Database"
            )
                .fallbackToDestructiveMigration()
                .build()
            return instance
        }
    }
}