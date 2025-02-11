package com.dicoding.mymvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import com.dicoding.mymvvm.data.repository.DesignTeamRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

// ViewModel class to manage and expose UI-related data for DesignTeam.
class DesignTeamViewModel(application: Application) : AndroidViewModel(application) {

    // Repository instance for accessing data operations
    private val repository: DesignTeamRepository = DesignTeamRepository(application)

    // MutableLiveData to store and observe the list of DesignTeam entities
    private var _getDesignTeam: MutableLiveData<List<DesignTeam>> = MutableLiveData()

    // Public LiveData for external observers to access the design team list
    val getDesignTeam: LiveData<List<DesignTeam>> get() = _getDesignTeam

    // Inserts a new DesignTeam entry in the database
    fun insert(designTeam: DesignTeam) = viewModelScope.launch {
        repository.insert(designTeam)
    }

    // Updates an existing DesignTeam entry in the database
    fun update(designTeam: DesignTeam) = viewModelScope.launch {
        repository.update(designTeam)
    }

    // Deletes a specific DesignTeam entry from the database
    fun delete(designTeam: DesignTeam) = viewModelScope.launch {
        repository.delete(designTeam)
    }

    // Deletes all DesignTeam entries from the database
    fun deleteAllDesignTeam() = viewModelScope.launch {
        repository.deleteAllNotes()
    }

    // Retrieves all DesignTeam entries and observes changes
    fun getDesignTeam() = repository.getAllDesignTeam()
        ?.onEach { result ->
            // Updates the MutableLiveData when new data is available
            _getDesignTeam.value = result
        }?.launchIn(viewModelScope)
}