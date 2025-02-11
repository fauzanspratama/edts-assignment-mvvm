package com.dicoding.mymvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import kotlinx.coroutines.flow.Flow

// @Dao marks this interface as a Data Access Object (DAO) in Room Database.
@Dao
interface DesignTeamDAO {

    // @Insert is used for inserting data into the database table.
    // This function accepts a DesignTeam object to be inserted into the table.
    @Insert
    fun insertTeam(designTeam: DesignTeam)

    // @Update is used to update existing data in the table.
    // Room will match the primary key to determine which row to update.
    @Update
    fun updateTeam(designTeam: DesignTeam)

    // @Delete is used to remove an entity from the database table.
    // This function accepts a DesignTeam object to be deleted.
    @Delete
    fun deleteTeam(designTeam: DesignTeam)

    // @Query is used to write custom SQL queries.
    // This query deletes all entries from the design_team table.
    @Query("DELETE FROM design_team")
    fun deleteAllTeam()

    // @Query with SELECT is used to retrieve all entries from the table.
    // It returns the result as a Flow<List<DesignTeam>> for reactive data fetching.
    @Query("SELECT * FROM design_team")
    fun getAllTeam(): Flow<List<DesignTeam>>
}
