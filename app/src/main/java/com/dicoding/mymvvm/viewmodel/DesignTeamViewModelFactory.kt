package com.dicoding.mymvvm.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Factory to create DesignTeamViewModel with application context
class DesignTeamViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    // Creates an instance of the specified ViewModel class
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DesignTeamViewModel::class.java)) {
            return DesignTeamViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}