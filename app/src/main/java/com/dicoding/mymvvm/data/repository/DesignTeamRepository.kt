package com.dicoding.mymvvm.data.repository

import android.app.Application
import com.dicoding.mymvvm.data.local.dao.DesignTeamDAO
import com.dicoding.mymvvm.data.local.database.EDTSDatabase
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

// Repository class that serves as an abstraction layer between the data source and the rest of the app.
class DesignTeamRepository(application: Application) {

    private val designTeamDAO: DesignTeamDAO?
    private val allDesignTeam: Flow<List<DesignTeam>>?

    init {
        // Initializes the database instance and retrieves the DAO.
        val database = EDTSDatabase.getInstance(application)
        designTeamDAO = database.designTeamDao()
        allDesignTeam = designTeamDAO.getAllTeam()
    }

    // Inserts a DesignTeam object into the database.
    // Executes the database operation on the IO dispatcher to avoid blocking the main thread.
    suspend fun insert(designTeam: DesignTeam) {
        withContext(Dispatchers.IO) {
            designTeamDAO?.insertTeam(designTeam)
        }
    }

    // Updates a DesignTeam object in the database.
    // This operation is also dispatched to the IO thread.
    suspend fun update(designTeam: DesignTeam) {
        withContext(Dispatchers.IO) {
            designTeamDAO?.updateTeam(designTeam)
        }
    }

    // Deletes a specific DesignTeam object from the database.
    suspend fun delete(designTeam: DesignTeam) {
        withContext(Dispatchers.IO) {
            designTeamDAO?.deleteTeam(designTeam)
        }
    }

    // Deletes all entries in the design_team table.
    suspend fun deleteAllNotes() {
        withContext(Dispatchers.IO) {
            designTeamDAO?.deleteAllTeam()
        }
    }

    // Retrieves all design team entries as a Flow for reactive data handling.
    fun getAllDesignTeam(): Flow<List<DesignTeam>>? = allDesignTeam
}
