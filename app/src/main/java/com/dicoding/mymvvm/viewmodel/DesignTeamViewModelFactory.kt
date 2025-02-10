package com.dicoding.mymvvm.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DesignTeamViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DesignTeamViewModel::class.java)) {
            return DesignTeamViewModel(application) as T
        }
        throw IllegalArgumentException("Unknow ViewModel Class")
    }
}